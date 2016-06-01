package com.groeps33.server.shared.lobby;

import com.groeps33.server.shared.UserAccount;
import com.groeps33.server.shared.game.GameAdministration;
import com.groeps33.server.shared.game.IGameServer;
import com.groeps33.server.shared.lobby.exceptions.AlreadyJoinedException;
import com.groeps33.server.shared.lobby.exceptions.InsufficientPermissionsException;
import com.groeps33.server.shared.lobby.exceptions.LobbyFullException;
import com.groeps33.server.shared.lobby.exceptions.IncorrectPasswordException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * @author Edwin
 *         Created on 5/28/2016
 */
public class Lobby extends UnicastRemoteObject implements ILobby {
    private final List<UserAccount> userAccountList;
    private final List<Message> messageList;
    private final Map<UserAccount, Long> pulseMap;
    private final String lobbyName;
    private final String password;
    private final int maximumPlayers;
    private final int id;
    private IGameServer startedGame;

    public Lobby(UserAccount creator, String lobbyName, String password, int maximumPlayers, int id) throws RemoteException {
        this.lobbyName = lobbyName;
        this.password = password;
        this.maximumPlayers = maximumPlayers;
        this.id = id;
        userAccountList = new ArrayList<>();
        userAccountList.add(creator);
        pulseMap = new HashMap<>();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (startedGame == null || !startedGame.isRunning()) {
                        long currentT = System.currentTimeMillis();
                        for (Map.Entry<UserAccount, Long> entry : pulseMap.entrySet()) {
                            if (currentT - entry.getValue() > 3000) {
                                removeUser(entry.getKey());
                            }
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 200);
        messageList = new ArrayList<>();
    }

    @Override
    public UserAccount getHost() {
        if (userAccountList.isEmpty()) {
            throw new IllegalStateException("No users in lobby. Lobby cannot be empty.");
        }

        return userAccountList.get(0);
    }

    @Override
    public void pulse(UserAccount userAccount) throws RemoteException {
        pulseMap.put(userAccount, System.currentTimeMillis());
    }

    @Override
    public IGameServer getGameServer() throws RemoteException {
        return startedGame;
    }

    @Override
    public List<Message> getMessages() throws RemoteException {
        return Collections.unmodifiableList(messageList);
    }

    @Override
    public void registerMessage(UserAccount userAccount, String message) throws RemoteException {
        messageList.add(new Message(userAccount, message));
    }


    @Override
    public void registerUser(UserAccount userAccount, String password) throws RemoteException, AlreadyJoinedException, IncorrectPasswordException, LobbyFullException {
        if (hasPassword() && !this.password.equals(password)) {
            throw new IncorrectPasswordException(lobbyName, password);
        }

        if (isFull()) {
            throw new LobbyFullException();
        }

        if (userAccountList.contains(userAccount)) {
            throw new AlreadyJoinedException(userAccount);
        }

        userAccountList.add(userAccount);
    }

    @Override
    public void removeUser(UserAccount userAccount) throws RemoteException {
//        if (userAccount.equals(host)) {
//            hostDisconnected();
//        }
        userAccountList.remove(userAccount);

        if (userAccountList.size() == 0) {
            LobbyAdministration.get().removeLobby(this);
        }
    }

//    @Override
//    public void broadcastMessage(IChatMessage message, UserAccount sender) throws RemoteException {
//        for (UserAccount userAccount : userAccountList) {
//            if (!userAccount.equals(sender)) {
//                userAccount.receiveMessage(message, sender);
//            }
//        }
//    }

//    @Override
//    public void broadcastReady(boolean ready, IClient sender) throws RemoteException {
//        for (IClient client : userAccountList) {
//            if (!client.equals(sender)) {
//                client.receiveReadyNotification(ready, sender);
//            }
//        }
//    }

    @Override
    public String getLobbyName() throws RemoteException {
        return lobbyName;
    }

    @Override
    public List<UserAccount> getRegisteredUserAccounts() throws RemoteException {
        return Collections.unmodifiableList(userAccountList);
    }

    @Override
    public void startGame(UserAccount userAccount) throws RemoteException, InsufficientPermissionsException {
        if (!userAccount.equals(getHost())) {
            throw new InsufficientPermissionsException("Only the host can start the game.");
        }

        startedGame = GameAdministration.get().registerGame();
    }

    @Override
    public String toString() {
        String returnString;
        try {
            returnString = this.getLobbyName();
            for (UserAccount c : this.userAccountList) {
                returnString += c.getUsername();
            }
            return returnString;
        } catch (RemoteException e) {
            returnString = "error";
            e.printStackTrace();
        }
        return returnString;
    }

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public int getMaximumPlayers() {
        return maximumPlayers;
    }

    @Override
    public int getPlayerCount() {
        return userAccountList.size();
    }

    @Override
    public boolean isFull() throws RemoteException {
        return userAccountList.size() >= maximumPlayers;
    }

    @Override
    public boolean hasPassword() throws RemoteException {
        return password != null && !password.isEmpty();
    }
}
