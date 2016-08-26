package cn.com.foton.viewhoder;




import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHoder {
	private SparseArray<View> mViews;

	int mPosition;
	View mConvertView;
	public ViewHoder(Context context,ViewGroup parent,int layoutid,int position){
		this.mPosition=position;
		this.mViews=new SparseArray<View>();
		mConvertView=LayoutInflater.from(context).inflate(layoutid, null);
		mConvertView.setTag(this);
		
	}
	public static ViewHoder get(Context context,View convertView,ViewGroup parent,int layoutid,int position) {
		// TODO Auto-generated constructor stub
		if(convertView==null){
			return new ViewHoder(context, parent, layoutid, position);
		}else{
			ViewHoder hoder = (ViewHoder) convertView.getTag();
			hoder.mPosition=position;
			return hoder;
		}
	}
	
	/**
	 * 通过id获取控件View
	 * @param Viewid
	 * @return
	 */
	public <T extends View> T getView(int Viewid){
		
		View view=mViews.get(Viewid);
		if(view==null){
			view =mConvertView.findViewById(Viewid);
			mViews.put(Viewid, view);
		}
		
		return (T)view;
	}
	public View getConvertView(){
		return mConvertView;
	}
	public ViewHoder setText(int ViewId,String text){
		TextView tv1=getView(ViewId);
		tv1.setText(text);
		return this;
	}
	public ViewHoder setImageResource(int ViewId,int ImageId){
		ImageView imageview=getView(ViewId);
		imageview.setImageResource(ImageId);
		return this;
	}
	public ViewHoder setImageBitmap(int ViewId,Bitmap bitmap){
		ImageView imageview=getView(ViewId);
		imageview.setImageBitmap(bitmap);
		return this;
	}
	public ViewHoder setImageURl(int ViewId,String imageurl){
		ImageView imageview=getView(ViewId);
		//imageview.setImageURI(imageurl);
		return this;
	}
	public int getPosition() {
		return mPosition;
	}
	
}
