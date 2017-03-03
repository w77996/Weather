package weather.wu.com.broadcast;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.orhanobut.logger.Logger;


public class BluetoothReceiver extends BroadcastReceiver{

	String pin = "1234";  //蓝牙的秘钥
	public BluetoothReceiver() {
		
	}

	//广播接收，当远程的蓝牙被发现时，调用
	@Override
	public void onReceive(Context context, Intent intent) {
		
		String action = intent.getAction(); //得到广播的action
		Logger.e("action1=", action);
		BluetoothDevice btDevice=null; //创建蓝牙device对象
		 //从intent中获取设备的对象
		btDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE); 
		
		if(BluetoothDevice.ACTION_FOUND.equals(action)){  //发现设备
			Logger.e("发现设备:", "["+btDevice.getName()+"]"+":"+btDevice.getAddress());
			
			if(btDevice.getName().contains("HC"))//连接
			{
				if (btDevice.getBondState() == BluetoothDevice.BOND_NONE) {  
					
					Logger.e("ywq", "attemp to bond:"+"["+btDevice.getName()+"]");
					try {
						//调用ClsUtils工具类，创建Bond
						ClsUtils.createBond(btDevice.getClass(), btDevice);
					} catch (Exception e) {
						e.printStackTrace();
					}
                }
			}else
				Logger.e("error", "Is faild");
		}else if(action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) //再次得到action，等于PAIRING_REQUEST
		{
			Logger.e("action2=", action);
			if(btDevice.getName().contains("HC"))
			{
				Logger.e("here", "OKOKOK");
				
				try {
					
					//确认配对
					ClsUtils.setPairingConfirmation(btDevice.getClass(), btDevice, true);
					//种植有序广播
					Logger.i("order...", "isOrderedBroadcast:"+isOrderedBroadcast()+",isInitialStickyBroadcast:"+isInitialStickyBroadcast());
					abortBroadcast();//没有终止会出现一闪而过的对话框
					//3.调用setPin进行配对..
					boolean ret = ClsUtils.setPin(btDevice.getClass(), btDevice, pin);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				Logger.e( "目标设备不是蓝牙设备");
			}
		}
	}
}