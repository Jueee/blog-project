package com.jueee.bean;

import lombok.Data;

@Data
public class MessageBody {
    /** 消息内容 */
    private String content;
    /** 广播转发的目标地址（告知 STOMP 代理转发到哪个地方） */
    private String destination;
}
