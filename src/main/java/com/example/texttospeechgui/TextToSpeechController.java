package com.example.texttospeechgui;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.util.ArrayList;

public class TextToSpeechController {
    private static final VoiceManager voiceManager;

    static {
        // Set the system property before initializing the VoiceManager
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        voiceManager = VoiceManager.getInstance();
    }

    public static ArrayList<String> getVoices() {
        ArrayList<String> voices = new ArrayList<>();
        for (Voice voice : voiceManager.getVoices()) {
            voices.add(voice.getName());
        }

        return voices;
    }

    public static ArrayList<String> getSpeedRates() {
        ArrayList<String> speedRates = new ArrayList<>();
        speedRates.add("140"); //normal
        speedRates.add("170"); //fast
        speedRates.add("200"); // very fast
        speedRates.add("100"); // slow
        speedRates.add("60"); // very slow

        return speedRates;
    }

    public static ArrayList<String> getVolumeLevels() {
        ArrayList<String> volumeLevels = new ArrayList<>();
        for(int i=0; i<=10; i++){
            volumeLevels.add(Integer.toString(i));
        }
        return volumeLevels;
    }

    public static void speak(String message, String voiceType, String rate, String volume ) {
        Voice voice = voiceManager.getVoice(voiceType);
        if (voice == null) {
            System.out.println("Voice not found");
            System.exit(1);
        }

        //allocate the resources for the voices.
        voice.allocate();

        // set the volume (0-10)
        voice.setVolume(Integer.parseInt(volume));

        // convert text to voice
        voice.speak(message);

        // important! deallocate the resources when done
        voice.deallocate();

    }

}
