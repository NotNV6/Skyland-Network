package rip.skyland.commons.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang.time.DurationFormatUtils;

import java.util.concurrent.TimeUnit;

@UtilityClass
public class TimeUtils {

    /**
     * Format an epoch time in words
     *
     * @param time the epoch time
     * @return the formatted time in words
     */
    public String formatWords(long time) {
        return DurationFormatUtils.formatDurationWords(time, true, true);
    }

    /**
     * Format an epoch time in words
     *
     * @param time the time
     * @param includeSeconds whether it should include the time in seconds
     * @return the formatted time in words
     */
    public String formatWordsMin(long time, boolean includeSeconds) {
        final long years = TimeUnit.MILLISECONDS.toDays(time) / 365;
        final long months = (TimeUnit.MILLISECONDS.toDays(time) / 12) - (years * 12);
        final long days = TimeUnit.MILLISECONDS.toDays(time) - ((TimeUnit.MILLISECONDS.toDays(time) / 12) * 30);
        final long hours = TimeUnit.MILLISECONDS.toHours(time) - (TimeUnit.MILLISECONDS.toDays(time) * 30);
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(time) - (TimeUnit.MILLISECONDS.toHours(time) * 60);
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(time) - (TimeUnit.MILLISECONDS.toMinutes(time) * 60);

        final StringBuilder builder = new StringBuilder();

        if (years != 0) {
            builder.append(years).append("y");
        }
        if (months != 0) {
            checkComma(years, builder);
            builder.append(months).append("m");
        }
        if (days != 0) {
            checkComma(months, builder);
            builder.append(days).append("d");
        }
        if (hours != 0) {
            checkComma(days, builder);
            builder.append(hours).append("h");
        }
        if (minutes != 0) {
            checkComma(hours, builder);
            builder.append(minutes).append("m");
        }
        if (seconds != 0 && includeSeconds) {
            checkComma(minutes, builder);
            builder.append(seconds).append("s");
        }

        if (builder.toString().isEmpty()) {
            builder.append("0s");
        }

        return builder.toString();
    }

    /**
     * Check whether there should be a comma
     *
     * @param l the time
     * @param builder the StringBuilder
     */
    private void checkComma(long l, StringBuilder builder) {
        if (l != 0) {
            builder.append(", ");
        }
    }

    /**
     * Format a String
     *
     * @param input the input to get formatted
     * @return the formatted string
     */
    public long format(String input) {
        if (input == null || input.isEmpty()) {
            return -1L;
        }

        long result = 0L;
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                number.append(c);
                continue;
            }

            String str;
            if (Character.isLetter(c) && !(str = number.toString()).isEmpty()) {
                result += convert(Integer.parseInt(str), c);
                number = new StringBuilder();
            }
        }

        return result;
    }

    /**
     * Convert a char to a time value
     *
     * @param value the value
     * @param unit the unit
     * @return the converted time
     */
    private long convert(int value, char unit) {
        switch (unit) {
            case 'y' | 'Y':
                return value * TimeUnit.DAYS.toMillis(365L);
            case 'M':
                return value * TimeUnit.DAYS.toMillis(30L);
            case 'd' | 'D':
                return value * TimeUnit.DAYS.toMillis(1L);
            case 'h' | 'H':
                return value * TimeUnit.HOURS.toMillis(1L);
            case 'm':
                return value * TimeUnit.MINUTES.toMillis(1L);
            case 's' | 'S':
                return value * TimeUnit.SECONDS.toMillis(1L);
            default:
                return -1L;
        }
    }
}


