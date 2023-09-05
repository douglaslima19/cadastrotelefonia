package com.bcb.cadastrotelefonia.dtos;

public record SmsDTO(String telefoneReceiver, String telefoneSender,boolean ehWhatts, String mensagem) {
}
