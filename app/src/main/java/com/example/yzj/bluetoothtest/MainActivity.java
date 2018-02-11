package com.example.yzj.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private BluetoothAdapter mBluetoothAdapter;

    private int REQUEST_ENABLE_BT = 100;

    //蓝牙列表
    private List<String> items = new ArrayList<>();

    //消息列表
    private List<String> message = new ArrayList<>();

    @BindView(R.id.open_bluetooth)
    Button open_bluetooth;
    @BindView(R.id.close_bluetooth)
    Button close_bluetooth;
    @BindView(R.id.search_bluetooth)
    Button search_bluetooth;
    @BindView(R.id.send_message)
    Button send_message;
    @BindView(R.id.bluetooth_list)
    ListView bluetooth_list;
    @BindView(R.id.messages)
    ListView messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        open_bluetooth.setOnClickListener(this);
        close_bluetooth.setOnClickListener(this);
        search_bluetooth.setOnClickListener(this);
        send_message.setOnClickListener(this);

        //初始化蓝牙
        init();

        //设置蓝牙搜索列表
        setBluetoothList();

        //设置消息列表
        setMessages();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_bluetooth:
                if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()){
                    //打开蓝牙
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, REQUEST_ENABLE_BT);
                }
                break;
            case R.id.close_bluetooth:
                if (mBluetoothAdapter != null || mBluetoothAdapter.isEnabled()){
                    //关闭蓝牙
                    mBluetoothAdapter.disable();
                }
                break;
            case R.id.search_bluetooth:
                Toast.makeText(MainActivity.this, "333", Toast.LENGTH_SHORT).show();
                break;
            case R.id.send_message:
                Toast.makeText(MainActivity.this, "444", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * 初始化蓝牙
     */
    private void init(){
        final BluetoothManager mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
    }

    /**
     * 设置蓝牙搜索列表
     */
    private void setBluetoothList(){
        //搜索到的蓝牙
        for (int i = 0; i < 20; i++){
            items.add("00:00:00:00:00:0" + i);
        }
        //创建一个list并存放搜索到的蓝牙
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i = 0; i < items.size(); i++){
            Map<String, Object> item = new HashMap<>();
            item.put("bluetooth_item", items.get(i));
            listItems.add(item);
        }

        //为ListView设置Adapter
        SimpleAdapter sa = new SimpleAdapter(this, listItems, R.layout.bluetooth_item, new String[]{"bluetooth_item"}, new int[]{R.id.bluetooth_item});
        bluetooth_list.setAdapter(sa);

        //为列表项设置单击事件
        bluetooth_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //第position项被单击时触发该方法
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //dialog对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示");
                builder.setMessage(items.get(position));
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"取消！",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    /**
     * 设置消息列表
     */
    private void setMessages(){
        //搜索到的蓝牙
        for (int i = 0; i < 20; i++){
            message.add("消息" + i);
        }
        //创建一个list并存放搜索到的蓝牙
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i = 0; i < message.size(); i++){
            Map<String, Object> item = new HashMap<>();
            item.put("message", message.get(i));
            listItems.add(item);
        }

        //为ListView设置Adapter
        SimpleAdapter sa = new SimpleAdapter(this, listItems, R.layout.message_item, new String[]{"message"}, new int[]{R.id.message_item});
        messages.setAdapter(sa);

        //为列表项设置单击事件
        messages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //第position项被单击时触发该方法
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //dialog对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示");
                builder.setMessage(message.get(position));
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"取消！",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

}
