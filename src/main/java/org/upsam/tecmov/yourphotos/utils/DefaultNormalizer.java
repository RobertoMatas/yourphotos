package org.upsam.tecmov.yourphotos.utils;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultNormalizer extends Normalizer {

	public DefaultNormalizer() {
	}

	public static String messageFormat(String pattern,
			String... messageArguments) {
		return MessageFormat.format(pattern, (Object[]) messageArguments);
	}

	public static String deleteHtmlTags(String html) {
		if (html == null)
			return "";
		Pattern pattern = Pattern.compile("<(/?[^>]+)>");
		Matcher matcher = pattern.matcher(html);
		return matcher.replaceAll("");
	}

	public static String normalize(String text) {
		String normalized = text.toLowerCase();
		normalized = removeAccents(normalized);
		normalized = removePunctuation(normalized);
		normalized = replaceEmptySpaces(normalized);
		return normalized;
	}

	public static String normalizeMinWord(String text) {
		String normalized = normalize(text);
		normalized = removeMinWord(normalized, MIN_WORD_SIZE);
		return normalized;
	}

	public static String normalizeCategory(String text) {
		String normalized = normalize(text);
		return normalized;
	}
}