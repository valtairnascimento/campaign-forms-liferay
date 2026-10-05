package io.github.valtairnascimento.campaignforms.page;

import java.util.List;

public interface BlockTypeRegistry {
	public BlockType getBlockType(String key);

	public List<BlockType> getBlockTypes();
}
