package org.upsam.tecmov.yourphotos.spring.converter;

import java.io.IOException;
import java.io.OutputStreamWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;
import org.upsam.tecmov.yourphotos.spring.assembler.PListAssembler;

public class PListConverter extends AbstractHttpMessageConverter<Object> {

	/**
	 * 
	 */
	private PListAssembler pListAssembler;

	/**
	 * @param pListAssembler
	 *            the pListAssembler to set
	 */
	@Autowired
	public void setpListAssembler(PListAssembler pListAssembler) {
		this.pListAssembler = pListAssembler;
	}

	public PListConverter() {
		super(new MediaType("application", "xml+plist"));
	}

	public PListConverter(MediaType supportedMediaType) {
		super(supportedMediaType);
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void writeInternal(Object t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		outputMessage.getHeaders().setContentType(
				new MediaType("application", "xml+plist"));
		FileCopyUtils.copy(pListAssembler.assemble(t), new OutputStreamWriter(
				outputMessage.getBody(), "UTF-8"));

	}

}
