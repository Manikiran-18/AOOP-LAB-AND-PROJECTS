// Common interface for all music sources
interface MusicPlayer {
    void play();
    void stop();
    void pause();
}

// Local file player class
class LocalFilePlayer {
    public void playLocalFile() {
        System.out.println("Playing music from a local file.");
    }

    public void stopLocalFile() {
        System.out.println("Stopping music from the local file.");
    }

    public void pauseLocalFile() {
        System.out.println("Pausing music from the local file.");
    }
}

// Adapter for LocalFilePlayer
class LocalFilePlayerAdapter implements MusicPlayer {
    private LocalFilePlayer localFilePlayer;

    public LocalFilePlayerAdapter(LocalFilePlayer localFilePlayer) {
        this.localFilePlayer = localFilePlayer;
    }

    public void play() {
        localFilePlayer.playLocalFile();
    }

    public void stop() {
        localFilePlayer.stopLocalFile();
    }

    public void pause() {
        localFilePlayer.pauseLocalFile();
    }
}

// Online streaming service class
class OnlineStreamingService {
    public void startStreaming() {
        System.out.println("Starting online streaming service.");
    }

    public void endStreaming() {
        System.out.println("Ending online streaming service.");
    }

    public void pauseStreaming() {
        System.out.println("Pausing online streaming service.");
    }
}

// Adapter for OnlineStreamingService
class OnlineStreamingServiceAdapter implements MusicPlayer {
    private OnlineStreamingService onlineStreamingService;

    public OnlineStreamingServiceAdapter(OnlineStreamingService onlineStreamingService) {
        this.onlineStreamingService = onlineStreamingService;
    }

    public void play() {
        onlineStreamingService.startStreaming();
    }

    public void stop() {
        onlineStreamingService.endStreaming();
    }

    public void pause() {
        onlineStreamingService.pauseStreaming();
    }
}

// Radio station class
class RadioStation {
    public void startRadio() {
        System.out.println("Starting radio station.");
    }

    public void stopRadio() {
        System.out.println("Stopping radio station.");
    }

    public void pauseRadio() {
        System.out.println("Pausing radio station.");
    }
}

// Adapter for RadioStation
class RadioStationAdapter implements MusicPlayer {
    private RadioStation radioStation;

    public RadioStationAdapter(RadioStation radioStation) {
        this.radioStation = radioStation;
    }

    public void play() {
        radioStation.startRadio();
    }

    public void stop() {
        radioStation.stopRadio();
    }

    public void pause() {
        radioStation.pauseRadio();
    }
}

// Base decorator class
abstract class MusicPlayerDecorator implements MusicPlayer {
    protected MusicPlayer decoratedMusicPlayer;

    public MusicPlayerDecorator(MusicPlayer decoratedMusicPlayer) {
        this.decoratedMusicPlayer = decoratedMusicPlayer;
    }

    public void play() {
        decoratedMusicPlayer.play();
    }

    public void stop() {
        decoratedMusicPlayer.stop();
    }

    public void pause() {
        decoratedMusicPlayer.pause();
    }
}

// Equalizer decorator class
class EqualizerDecorator extends MusicPlayerDecorator {
    public EqualizerDecorator(MusicPlayer decoratedMusicPlayer) {
        super(decoratedMusicPlayer);
    }

    public void play() {
        super.play();
        addEqualizer();
    }

    private void addEqualizer() {
        System.out.println("Applying equalizer settings.");
    }
}

// Volume control decorator class
class VolumeControlDecorator extends MusicPlayerDecorator {
    public VolumeControlDecorator(MusicPlayer decoratedMusicPlayer) {
        super(decoratedMusicPlayer);
    }

    public void play() {
        super.play();
        adjustVolume();
    }

    private void adjustVolume() {
        System.out.println("Adjusting volume control.");
    }
}

// Main class to combine all the patterns and simulate the music streaming application
public class Main {
    public static void main(String[] args) {
        // Create music players
        MusicPlayer localFilePlayer = new LocalFilePlayerAdapter(new LocalFilePlayer());
        MusicPlayer onlineStreamingService = new OnlineStreamingServiceAdapter(new OnlineStreamingService());
        MusicPlayer radioStation = new RadioStationAdapter(new RadioStation());

        // Add features to music players
        MusicPlayer equalizedLocalFilePlayer = new EqualizerDecorator(localFilePlayer);
        MusicPlayer volumeControlledOnlineStreaming = new VolumeControlDecorator(onlineStreamingService);
        MusicPlayer fullyDecoratedRadioStation = new VolumeControlDecorator(new EqualizerDecorator(radioStation));

        // Play music with different sources and features
        System.out.println("Playing Local File with Equalizer:");
        equalizedLocalFilePlayer.play();

        System.out.println("\nPlaying Online Streaming Service with Volume Control:");
        volumeControlledOnlineStreaming.play();

        System.out.println("\nPlaying Radio Station with Equalizer and Volume Control:");
        fullyDecoratedRadioStation.play();
    }
}
