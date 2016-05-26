package com.groeps33.gui.settings;

import com.groeps33.gui.ValleyFX;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
        boolean audioFX = (Boolean) settings.get("audioFX");
        boolean audioMusic = (Boolean) settings.get("audioMusic");

        audioFxCheckbox.setSelected(audioFX);
        audioMusicCheckbox.setSelected(audioMusic);
    }

    @FXML
    private void back() throws IOException {
        boolean audioFX = audioFxCheckbox.isSelected();
        boolean audioMusic = audioMusicCheckbox.isSelected();
        ValleyFX.saveSettings(audioFX, audioMusic);
        ValleyFX.changeScene(getClass().getResource("../menu/menu.fxml"));
    }
}
