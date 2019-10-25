public class TrackModel<T> {

    public <TFailure> Track<T, TFailure> track() {
        return new Track(this);
    }
}
