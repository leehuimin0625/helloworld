package com.example.helloworld.whatsappclone;

class Chat {
    private String chatName;
    private String latestMessage;

    public String getChatName(){
        return chatName;
    }

    public void setChatName(String chatName){
        this.chatName = chatName;
    }

    public String getLatestMessage(){
        return latestMessage;
    }

    public void setLatestMessage(String latestMessage){
        this.latestMessage = latestMessage;
    }
}

