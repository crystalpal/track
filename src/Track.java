import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Track<T, TFailure>
{
    protected T model;
    protected List errors;

    public List<TFailure> getErrors() {
        return this.errors;
    }

    public Track(){}

    public Track(final T model) {
        this.model = model;
        errors = new ArrayList<>();
    }

    public <TSuccess, TFailure> Track<TSuccess, TFailure> next(
            final Function<T, Boolean> condition,
            final Function<T, TSuccess> mapper,
            final Supplier<TFailure> logger) {
        if (model != null && !condition.apply(model)){
            this.errors.add(logger.get());
        }
        return new Success(this, mapper.apply(model));
    }

    public <TSuccess, TFailure> Track<TSuccess, TFailure> next(
            final Function<T, Boolean> condition,
            final Consumer<T> enricher,
            final Supplier<TFailure> logger) {
        if (model != null && !condition.apply(model)){
            this.errors.add(logger.get());
        }
        enricher.accept(model);
        return new Success(this, model);
    }

    public <TFailure> Track<T, TFailure> next(
            final Function<T, Boolean> condition,
            final Supplier<TFailure> logger) {
        if (model != null && !condition.apply(model)){
            this.errors.add(logger.get());
        }
        return new Success<>(this, model);
    }

    public <TFailure> Track<T, TFailure> next(
            final Function<T, Boolean> condition,
            final Function<T, TFailure> handler) {
        if (model != null && !condition.apply(model)){
            this.errors.add(handler.apply(model));
        }
        return new Success<>(this, model);
    }

    public <TSuccess, TFailure> Track<TSuccess, TFailure> fail(
            final Function<T, Boolean> condition,
            final Function<T, TSuccess> mapper,
            final Supplier<TFailure> logger) {
        if (model != null && condition.apply(model)){
            return new Success(this, mapper.apply(model));
        }
        this.errors.add(logger.get());
        return new Failure(this);
    }

    public <TSuccess, TFailure> Track<TSuccess, TFailure> fail(
            final Function<T, Boolean> condition,
            final Consumer<T> enricher,
            final Supplier<TFailure> logger) {
        if (model != null && condition.apply(model)){
            enricher.accept(model);
            return new Success(this, model);
        }
        this.errors.add(logger.get());
        return new Failure(this);
    }

    public <TSuccess, TFailure> Track<TSuccess, TFailure> fail(
            final Function<T, Boolean> condition,
            final Supplier<TFailure> logger) {
        if (model != null && condition.apply(model)){
            return new Success(this, model);
        }
        this.errors.add(logger.get());
        return new Failure(this);
    }
}