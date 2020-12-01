package com.example.demo.service;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @since 1.8
 */
public final class Optional<T> {
        private static final Optional<?> EMPTY = new Optional<>();

        private final T value;

        private Optional() {
                this.value = null;
        }

        // 返回一个空的 Optional实例
        public static<T> Optional<T> empty() {
                @SuppressWarnings("unchecked")
                Optional<T> t = (Optional<T>) EMPTY;
                return t;
        }

        private Optional(T value) {
                this.value = Objects.requireNonNull(value);
        }

        // 返回具有 Optional的当前非空值的Optional
        public static <T> Optional<T> of(T value) {
                return new Optional<>(value);
        }

        // 返回一个 Optional指定值的Optional，如果非空，则返回一个空的 Optional
        public static <T> Optional<T> ofNullable(T value) {
                return value == null ? empty() : of(value);
        }

        // 如果Optional中有一个值，返回值，否则抛出 NoSuchElementException 。
        public T get() {
                if (value == null) {
                        //throw new NoSuchElementException("No value present");
                }
                return value;
        }

        // 返回true如果存在值，否则为 false
        public boolean isPresent() {
                return value != null;
        }

        // 如果存在值，则使用该值调用指定的消费者，否则不执行任何操作。
        public void ifPresent(Consumer<? super T> consumer) {
                if (value != null)
                        consumer.accept(value);
        }

        // 如果一个值存在，并且该值给定的谓词相匹配时，返回一个 Optional描述的值，否则返回一个空的 Optional
        public Optional<T> filter(Predicate<? super T> predicate) {
                Objects.requireNonNull(predicate);
                if (!isPresent())
                        return this;
                else
                        return predicate.test(value) ? this : empty();
        }

        // 如果存在一个值，则应用提供的映射函数，如果结果不为空，则返回一个 Optional结果的 Optional 。
        public<U> Optional<U> map(Function<? super T, ? extends U> mapper) {
                Objects.requireNonNull(mapper);
                if (!isPresent())
                        return empty();
                else {
                        return Optional.ofNullable(mapper.apply(value));
                }
        }

        // 如果一个值存在，应用提供的 Optional映射函数给它，返回该结果，否则返回一个空的 Optional 。
        public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
                Objects.requireNonNull(mapper);
                if (!isPresent())
                        return empty();
                else {
                        return Objects.requireNonNull(mapper.apply(value));
                }
        }

        // 如果值存在，就返回值，不存在就返回指定的其他值
        public T orElse(T other) {
                return value != null ? value : other;
        }


        public T orElseGet(Supplier<? extends T> other) {
                return value != null ? value : other.get();
        }

        public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
                if (value != null) {
                        return value;
                } else {
                        throw exceptionSupplier.get();
                }
        }
}
