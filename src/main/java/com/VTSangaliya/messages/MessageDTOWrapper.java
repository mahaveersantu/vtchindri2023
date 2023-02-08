package com.VTSangaliya.messages;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown =true)

public class MessageDTOWrapper {
	
	@JsonProperty("data")
    private List<MessageDTO> messageDTO;

	
	public List<MessageDTO> getMessageDTO() {
		return messageDTO;
	}

	public void setMessageDTO(List<MessageDTO> messageDTO) {
		this.messageDTO = messageDTO;
	}
	
	

}
