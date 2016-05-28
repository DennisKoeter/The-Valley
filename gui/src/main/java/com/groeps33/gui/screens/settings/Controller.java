package com.groeps33.gui.screens.settings;

 import com.groeps33.gui.application.Constants;
 import com.groeps33.gui.application.ValleyFX;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {
    @FXML
    CheckBox audioFxCheckbox;

    @FXML
    CheckBox audioMusicCheckbox;

    @FXML
    private void initialize() {
        HashMap settings = ValleyFX.getSettings();
        boolean audioFX = (Boolean) settings.get(Constants.AUDIOFX_KEY);
        boolean audioMusic = (Boolean) settings.get(Constants.AUDIOMUSIC_KEY);

        audioFxCheckbox.setSelected(audioFX);
        audioMusicCheckbox.setSelected(audioMusic);
    }

    @FXML
    private void back() throws IOException {
        boolean audioFX = audioFxCheckbox.isSelected();
        boolean audioMusic = audioMusicCheckbox.isSelected();
        ValleyFX.saveSettings(audioFX, audioMusic);
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.MENU_PATH));
    }
}
