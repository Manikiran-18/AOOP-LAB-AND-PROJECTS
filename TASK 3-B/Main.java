// Common interface for music sources
interface MusicSource {
    void start();
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
class LocalFilePlayerAdapter implements MusicSource {
    private LocalFilePlayer localFilePlayer;

    public LocalFilePlayerAdapter(LocalFilePlayer localFilePlayer) {
        this.localFilePlayer = localFilePlayer;
    }

    public void start() {
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
class OnlineStreamingServiceAdapter implements MusicSource {
    private OnlineStreamingService onlineStreamingService;

    public OnlineStreamingServiceAdapter(OnlineStreamingService onlineStreamingService) {
        this.onlineStreamingService = onlineStreamingService;
    }

    public void start() {
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
class RadioStationAdapter implements MusicSource {
    private RadioStation radioStation;

    public RadioStationAdapter(RadioStation radioStation) {
        this.radioStation = radioStation;
    }

    public void start() {
        radioStation.startRadio();
    }

    public void stop() {
        radioStation.stopRadio();
    }

    public void pause() {
        radioStation.pauseRadio();
    }
}

// Bridge interface for playback functionality
interface Playback {
    void play();
    void stop();
    void pause();
}

// MusicPlayer class that uses the Playback interface
class MusicPlayer {
    private Playback playback;

    public MusicPlayer(Playback playback) {
        this.playback = playback;
    }

    public void play() {
        playback.play();
    }

    public void stop() {
        playback.stop();
    }

    public void pause() {
        playback.pause();
    }
}

// Base decorator class
abstract class MusicPlayerDecorator implements Playback {
    protected Playback decoratedPlayback;

    public MusicPlayerDecorator(Playback decoratedPlayback) {
        this.decoratedPlayback = decoratedPlayback;
    }

    public void play() {
        decoratedPlayback.play();
    }

    public void stop() {
        decoratedPlayback.stop();
    }

    public void pause() {
        decoratedPlayback.pause();
    }
}

// Equalizer decorator class
class EqualizerDecorator extends MusicPlayerDecorator {
    public EqualizerDecorator(Playback decoratedPlayback) {
        super(decoratedPlayback);
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
    public VolumeControlDecorator(Playback decoratedPlayback) {
        super(decoratedPlayback);
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
        // Create music sources
        MusicSource localFilePlayer = new LocalFilePlayerAdapter(new LocalFilePlayer());
        MusicSource onlineStreamingService = new OnlineStreamingServiceAdapter(new OnlineStreamingService());
        MusicSource radioStation = new RadioStationAdapter(new RadioStation());

        // Create playback implementations
        Playback localFilePlayback = new Playback() {
            public void play() { localFilePlayer.start(); }
            public void stop() { localFilePlayer.stop(); }
            public void pause() { localFilePlayer.pause(); }
        };
        Playback onlineStreamingPlayback = new Playback() {
            public void play() { onlineStreamingService.start(); }
            public void stop() { onlineStreamingService.stop(); }
            public void pause() { onlineStreamingService.pause(); }
        };
        Playback radioStationPlayback = new Playback() {
            public void play() { radioStation.start(); }
            public void stop() { radioStation.stop(); }
            public void pause() { radioStation.pause(); }
        };

        // Create MusicPlayer instances with decorators
        MusicPlayer localFilePlayerWithFeatures = new MusicPlayer(new EqualizerDecorator(localFilePlayback));
        MusicPlayer onlineStreamingWithFeatures = new MusicPlayer(new VolumeControlDecorator(onlineStreamingPlayback));
        MusicPlayer radioStationWithFeatures = new MusicPlayer(new VolumeControlDecorator(new EqualizerDecorator(radioStationPlayback)));

        // Play music with different sources and features
        System.out.println("Playing Local File with Equalizer:");
        localFilePlayerWithFeatures.play();

        System.out.println("\nPlaying Online Streaming Service with Volume Control:");
        onlineStreamingWithFeatures.play();

        System.out.println("\nPlaying Radio Station with Equalizer and Volume Control:");
        radioStationWithFeatures.play();
    }
}
