package player;

import java.io.File;

import javazoom.jlgui.basicplayer.*;

public class MusicPlayer {
	private BasicPlayer basicPlayer = null;
	private File filePlaying;
	
	public MusicPlayer(){
		basicPlayer = new BasicPlayer();
	}
	
	public void play (File file){
		filePlaying = file;
		try {
			basicPlayer.open(file);
			basicPlayer.play();
		}
		catch (Exception e){}
	}
	
	public File getMusicPlaying (){
		return filePlaying;
	}
	
	public void stop(){
		filePlaying = null;
		try {
			basicPlayer.stop();
		}
		catch (BasicPlayerException e){
		}
	}
	
	public void setVolume(double vol, double volMax){
		try{
			basicPlayer.setGain(vol/volMax);
		}
		catch (BasicPlayerException e){
		}
	}
}
