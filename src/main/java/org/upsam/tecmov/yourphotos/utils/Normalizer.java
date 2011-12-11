package org.upsam.tecmov.yourphotos.utils;

public abstract class Normalizer {

	public static final int MIN_WORD_SIZE = 2;
	public static final String SEP = "-";

	public Normalizer() {
	}

	protected static String removePunctuation(String s) {
		s = s.replaceAll("/", "-");
		s = s.replaceAll("[\\W&&[\\S&&[^-]]]", "");
		return s;
	}

	protected static String removeAccents(String s) {
		s = s.replaceAll("[\340\342\341]", "a");
		s = s.replaceAll("[\350\351\352\353]", "e");
		s = s.replaceAll("[\357\356\355]", "i");
		s = s.replaceAll("[\364\363\362]", "o");
		s = s.replaceAll("[\373\371\372\374]", "u");
		s = s.replaceAll("[\347]", "c");
		s = s.replaceAll("[\361]", "n");
		return s;
	}

	protected static String replaceEmptySpaces(String s) {
		String words[] = s.split(" ");
		StringBuffer hrf = new StringBuffer(1024);
		for (int i = 0, c = words.length; i < c; ++i) {
			if (words[i].length() > 0 && !words[i].equalsIgnoreCase("-")) {

				hrf.append(words[i]);
				hrf.append("-");
			}
		}
		if (hrf.length() > 0) {
			hrf.deleteCharAt(hrf.length() - 1);
		}

		return hrf.toString();
	}

	protected static String removeMinWord(String string, int minSize) {
		string = string.replaceAll("\\W(\\w{1," + minSize + "})\\W", "-");
		string = string.replaceAll("(^(\\w{1," + minSize + "})\\W)|(\\W(\\w{1,"
				+ minSize + "})$)", "");
		string = string.replaceAll("---", "-");
		return string;
	}

}