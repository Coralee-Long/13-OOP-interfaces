package com.cora;

public class Main {
    public static void main(String[] args) {



        Playable playable1 = new MusicPlayer();
        Playable playable2 = new VideoPlayer();

        MediaController mc = new MediaController();

        System.out.println(mc.playMedia(playable1));
        System.out.println(mc.playMedia(playable2));









    }
}