package org.upsam.tecmov.yourphotos.spring.assembler;

public interface Assembler<SRC, DEST> {

	DEST assemble(SRC source);
}
