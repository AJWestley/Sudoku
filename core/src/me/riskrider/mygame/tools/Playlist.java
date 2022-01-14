package me.riskrider.mygame.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;

import java.util.Random;

public class Playlist {

    public Music[] songs;

    public int numPlaying;

    public Playlist () {

        songs = new Music[5];

        songs[0] = Gdx.audio.newMusic(Gdx.files.internal("music/bossa-brazil-bossa-nova-2966.mp3"));
        songs[1] = Gdx.audio.newMusic(Gdx.files.internal("music/chillout-cafe-4176.mp3"));
        songs[2] = Gdx.audio.newMusic(Gdx.files.internal("music/love-romantic-music-instrumental-9407.mp3"));
        songs[3] = Gdx.audio.newMusic(Gdx.files.internal("music/nightlife-michael-kobrin-95bpm-3783.mp3"));
        songs[4] = Gdx.audio.newMusic(Gdx.files.internal("music/summertime-dreamy-piano-ballad-1141.mp3"));

        Random rand = new Random();

        numPlaying = rand.nextInt(5);

    }

    public void play () {
        songs[numPlaying].play();
    }

    public void setVolume (float volume) {
        for (int i = 0; i < songs.length; i++) {
            songs[i].setVolume(volume);
        }
    }

    public void checkNextSong () {
        if (!songs[numPlaying].isPlaying()) {
            numPlaying++;
            if (numPlaying >= songs.length) numPlaying = 0;
            songs[numPlaying].play();
        }
    }

    public void checkNextSong (int num) {
        songs[numPlaying].stop();
        numPlaying += num;
        if (numPlaying >= songs.length) numPlaying = 0;
        songs[numPlaying].play();
    }

}
