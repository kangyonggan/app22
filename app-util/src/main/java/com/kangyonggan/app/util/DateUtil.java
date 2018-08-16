package com.kangyonggan.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期时间工具类
 *
 * @author kangyonggan
 * @since 5/4/18
 */
public final class DateUtil {

    /**
     * 系统默认时区
     */
    private static final ZoneId ZONE_ID = ZoneId.systemDefault();

    /**
     * 格式：yyyyMMddHHmmssSSS
     */
    public static final String PATTERN_FULL_DATE_TIME = "yyyyMMddHHmmssSSS";

    /**
     * 日期时间毫秒格式化
     */
    public static final SimpleDateFormat FULL_DATE_TIME_FORMAT = new SimpleDateFormat(PATTERN_FULL_DATE_TIME);

    /**
     * 格式：yyyyMMddHHmmss
     */
    public static final String PATTERN_DATE_TIME = "yyyyMMddHHmmss";

    /**
     * 日期时间格式化
     */
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(PATTERN_DATE_TIME);

    /**
     * 格式：yyyyMMdd
     */
    public static final String PATTERN_DATE = "yyyyMMdd";

    /**
     * 日期格式化
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(PATTERN_DATE);

    /**
     * 格式：HHmmss
     */
    public static final String PATTERN_TIME = "HHmmss";

    /**
     * 时间格式化
     */
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(PATTERN_TIME);

    /**
     * 私有构造, 任何时候都不能实例化
     */
    private DateUtil() {

    }

    /**
     * 把Date转为LocalDateTime
     *
     * @param date 日期
     * @return 返回LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZONE_ID);
    }

    /**
     * 把LocalDateTime转为Date
     *
     * @param localDateTime localDateTime格式的日期
     * @return 返回Date日期
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZONE_ID).toInstant());
    }

    /**
     * 获取当前日期
     *
     * @return 返回当前日期
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 格式化日期
     *
     * @param date    日期
     * @param pattern 格式
     * @return 返回格式化之后的字符串
     */
    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 格式化成14位字符串：yyyyMMddHHmmss
     *
     * @param date 日期
     * @return 返回格式化之后的字符串
     */
    public static String formatToFullDateTime(Date date) {
        synchronized (FULL_DATE_TIME_FORMAT) {
            return FULL_DATE_TIME_FORMAT.format(date);
        }
    }

    /**
     * 格式化成14位字符串：yyyyMMddHHmmss
     *
     * @param date 日期
     * @return 返回格式化之后的字符串
     */
    public static String formatToDateTime(Date date) {
        synchronized (DATE_TIME_FORMAT) {
            return DATE_TIME_FORMAT.format(date);
        }
    }

    /**
     * 格式化成8位字符串：yyyyMMdd
     *
     * @param date 日期
     * @return 返回格式化之后的字符串
     */
    public static String formatToDate(Date date) {
        synchronized (DATE_FORMAT) {
            return DATE_FORMAT.format(date);
        }
    }

    /**
     * 格式化成6位字符串：HHmmss
     *
     * @param date 日期
     * @return 返回格式化之后的字符串
     */
    public static String formatToTime(Date date) {
        synchronized (TIME_FORMAT) {
            return TIME_FORMAT.format(date);
        }
    }

    /**
     * 格式化当前日期
     *
     * @param pattern 格式
     * @return 返回格式化之后的字符串
     */
    public static String formatNow(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化当前日期成14位字符串：yyyyMMddHHmmss
     *
     * @return 返回格式化之后的字符串
     */
    public static String formatNowToFullDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN_FULL_DATE_TIME));
    }

    /**
     * 格式化当前日期成14位字符串：yyyyMMddHHmmss
     *
     * @return 返回格式化之后的字符串
     */
    public static String formatNowToDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN_DATE_TIME));
    }

    /**
     * 格式化当前日期成8位字符串：yyyyMMdd
     *
     * @return 返回格式化之后的字符串
     */
    public static String formatNowToDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN_DATE));
    }

    /**
     * 格式化当前日期成6位字符串：HHmmss
     *
     * @return 返回格式化之后的字符串
     */
    public static String formatNowToTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN_TIME));
    }

    /**
     * 在指定日期的基础上加纳秒
     *
     * @param date  日期
     * @param nanos 为负数则是减去纳秒
     * @return 返回计算后的日期
     */
    public static Date plusNanos(Date date, long nanos) {
        return toDate(toLocalDateTime(date).plusNanos(nanos));
    }

    /**
     * 在指定日期的基础上加秒
     *
     * @param date    日期
     * @param seconds 为负数则是减去秒
     * @return 返回计算后的日期
     */
    public static Date plusSeconds(Date date, long seconds) {
        return toDate(toLocalDateTime(date).plusSeconds(seconds));
    }

    /**
     * 在指定日期的基础上加分
     *
     * @param date    日期
     * @param minutes 为负数则是减去分
     * @return 返回计算后的日期
     */
    public static Date plusMinutes(Date date, long minutes) {
        return toDate(toLocalDateTime(date).plusMinutes(minutes));
    }

    /**
     * 在指定日期的基础上加时
     *
     * @param date  日期
     * @param hours 为负数则是减去时
     * @return 返回计算后的日期
     */
    public static Date plusHours(Date date, long hours) {
        return toDate(toLocalDateTime(date).plusHours(hours));
    }

    /**
     * 在指定日期的基础上加天
     *
     * @param date 日期
     * @param days 为负数则是减去天
     * @return 返回计算后的日期
     */
    public static Date plusDays(Date date, long days) {
        return toDate(toLocalDateTime(date).plusDays(days));
    }

    /**
     * 在指定日期的基础上加周
     *
     * @param date  日期
     * @param weeks 为负数则是减去周
     * @return 返回计算后的日期
     */
    public static Date plusWeeks(Date date, long weeks) {
        return toDate(toLocalDateTime(date).plusWeeks(weeks));
    }

    /**
     * 在指定日期的基础上加月
     *
     * @param date   日期
     * @param months 为负数则是减去月
     * @return 返回计算后的日期
     */
    public static Date plusMonths(Date date, long months) {
        return toDate(toLocalDateTime(date).plusMonths(months));
    }

    /**
     * 在指定日期的基础上加年
     *
     * @param date  日期
     * @param years 为负数则是减去年
     * @return 返回计算后的日期
     */
    public static Date plusYears(Date date, long years) {
        return toDate(toLocalDateTime(date).plusYears(years));
    }

    /**
     * 计算两个日期的差(结束日期减开始日期)
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 返回两个时间的差
     */
    public static Duration duration(Date startDate, Date endDate) {
        return Duration.between(startDate.toInstant(), endDate.toInstant());
    }

    /**
     * 计算两个日期相差的纳秒(结束日期减开始日期)
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 返回相差的纳秒
     */
    public static long durationNanos(Date startDate, Date endDate) {
        return duration(startDate, endDate).toNanos();
    }

    /**
     * 计算两个日期相差的毫秒(结束日期减开始日期)
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 返回相差的毫秒
     */
    public static long durationMillis(Date startDate, Date endDate) {
        return duration(startDate, endDate).toMillis();
    }

    /**
     * 计算两个日期相差的秒(结束日期减开始日期)
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 返回相差的秒
     */
    public static long durationSeconds(Date startDate, Date endDate) {
        return duration(startDate, endDate).toMillis() / 1000;
    }

    /**
     * 计算两个日期相差的分(结束日期减开始日期)
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 返回相差的分
     */
    public static long durationMinutes(Date startDate, Date endDate) {
        return duration(startDate, endDate).toMinutes();
    }

    /**
     * 计算两个日期相差的时(结束日期减开始日期)
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 返回相差的时
     */
    public static long durationHours(Date startDate, Date endDate) {
        return duration(startDate, endDate).toHours();
    }

    /**
     * 计算两个日期相差的日(结束日期减开始日期)
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 返回相差的日
     */
    public static long durationDays(Date startDate, Date endDate) {
        return duration(startDate, endDate).toDays();
    }

    /**
     * 把字符串解析成指定格式的日期
     *
     * @param str     字符串
     * @param pattern 日期格式
     * @return 返回解析后的日期
     * @throws ParseException 解析异常后抛此异常
     */
    public static Date parse(String str, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(str);
    }

    /**
     * 把字符串解析成17位日期时间毫秒格式的日期
     *
     * @param str 字符串
     * @return 返回解析后的日期
     * @throws ParseException 解析异常后抛此异常
     */
    public static Date parseFullDateTime(String str) throws ParseException {
        synchronized (FULL_DATE_TIME_FORMAT) {
            return FULL_DATE_TIME_FORMAT.parse(str);
        }
    }

    /**
     * 把字符串解析成14位日期时间格式的日期
     *
     * @param str 字符串
     * @return 返回解析后的日期
     * @throws ParseException 解析异常后抛此异常
     */
    public static Date parseDateTime(String str) throws ParseException {
        synchronized (DATE_TIME_FORMAT) {
            return DATE_TIME_FORMAT.parse(str);
        }
    }

    /**
     * 把字符串解析成8位日期格式的日期
     *
     * @param str 字符串
     * @return 返回解析后的日期
     * @throws ParseException 解析异常后抛此异常
     */
    public static Date parseDate(String str) throws ParseException {
        synchronized (DATE_FORMAT) {
            return DATE_FORMAT.parse(str);
        }
    }

    /**
     * 把字符串解析成6位日期格式的日期
     *
     * @param str 字符串
     * @return 返回解析后的日期
     * @throws ParseException 解析异常后抛此异常
     */
    public static Date parseTime(String str) throws ParseException {
        synchronized (TIME_FORMAT) {
            return TIME_FORMAT.parse(str);
        }
    }

}
