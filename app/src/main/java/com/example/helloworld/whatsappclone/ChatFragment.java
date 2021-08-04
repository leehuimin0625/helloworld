package com.example.helloworld.whatsappclone;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helloworld.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChatFragment extends Fragment {
    private ListView chatListView;
    private ArrayList<Chat> chats = new ArrayList<>();

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateChatList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setListeners();
    }
    
    private void findViews(View view){
        chatListView = view.findViewById(R.id.listview);
    }

    private void populateChatList() {
        Chat chat1 = new Chat();
        chat1.setChatName("Menhera");
        chat1.setLatestMessage("How are you?");

        Chat chat2 = new Chat();
        chat2.setChatName("TPRG343");
        chat2.setLatestMessage("Join Zoom Meeting");

        chats.add(chat1);
        chats.add(chat2);
    }

    private void setListeners() {
        ChatlistAdapter adapter = new ChatlistAdapter(getContext(),chats);
        chatListView.setAdapter(adapter);
    }

    private class ChatlistAdapter extends ArrayAdapter<Chat> {
        private ArrayList<Chat>data;
        private Context context;

        ChatlistAdapter(Context context, ArrayList<Chat> list){
            super(context, R.layout.single_row_simple_listview);
            this.data =list;
            this.context = context;
        }

        @Nullable
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            View mainView;
            Chat chatItem = data.get(position);

            if(convertView == null) {
                mainView = View.inflate(context, R.layout.chat_list_item, null);
                ViewHolder holder = new ViewHolder();
                holder.tvChatName = mainView.findViewById(R.id.chatName);
                holder.tvChatLatestMessage = mainView.findViewById(R.id.latestChatMessage);

                holder.tvChatName.setText(chatItem.getChatName());
                holder.tvChatLatestMessage.setText(chatItem.getLatestMessage());

                mainView.setTag(holder);
            } else {
                mainView = convertView;
                ViewHolder holder = (ViewHolder) convertView.getTag();
                holder.tvChatName.setText(chatItem.getChatName());
                holder.tvChatLatestMessage.setText(chatItem.getLatestMessage());
            }

            return mainView;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        class ViewHolder{
            TextView tvChatName;
            TextView tvChatLatestMessage;
        }
    }
}