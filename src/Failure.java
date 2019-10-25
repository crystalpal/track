import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Failure<T, TFailure> extends Track<T, TFailure>
{
    public Failure(final Track track)
    {
        this(track, null);
    }

    public Failure(final Track track, final T model) {
        this.model = model;
        errors = track.getErrors();
    }

    public <TSuccess, TFailure> Track<TSuccess, TFailure> next(
            final Function<T, Boolean> condition,
            final Function<T, TSuccess> mapper,
            final Supplier<TFailure> logger) {
        return new Failure<>(this, null);
    }

    public <TSuccess, TFailure> Track<TSuccess, TFailure> next(
            final Function<T, Boolean> condition,
            final Consumer<T> enricher,
            final Supplier<TFailure> logger) {
        return new Failure<>(this, null);
    }

    public <TSuccess, TFailure> Track<TSuccess, TFailure> fail(
            final Function<T, Boolean> condition,
            final Function<T, TSuccess> mapper,
            final Supplier<TFailure> logger) {
       return next(condition, mapper, logger);
    }
}