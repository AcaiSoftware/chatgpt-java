package gg.acai.chatgpt.exception;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 10.12.2022 20:33
 */
public record ParsedExceptionEntry(String exceptionKeywords, Class<? extends Exception> exceptionClass) {}
