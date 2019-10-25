public class Success<T, TFailure> extends Track<T, TFailure>
{
    public Success(final Track track, final T value) {
        model = value;
        errors = track.errors;
    }
}