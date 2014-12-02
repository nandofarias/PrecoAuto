package br.com.fiap.precoauto.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.fiap.precoauto.R;
import br.com.fiap.precoauto.VO.Versao;
 
 
 
public class VersaoAdapter extends BaseAdapter {
    private List<Versao> versoes;
    private LayoutInflater mLayoutInflater;
 
    public VersaoAdapter(Context context,List<Versao> versoes){
 
    	this.versoes = versoes;
 
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
 
    @Override
    public int getCount() {
        return versoes.size();
    }
 
    @Override
    public Object getItem(int i) {
        return versoes.get(i);
    }
 
    @Override
    public long getItemId(int i) {
        return 0;
    }
 
    @Override
 
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
 
        if (view == null) {
            holder = new ViewHolder();
 
            view = mLayoutInflater.inflate(R.layout.item, null);
            holder.itemName = (TextView) view.findViewById(R.id.list_item_text_view);
 
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
 
        String stringItem = versoes.get(position).getNome();
        if (stringItem != null) {
            if (holder.itemName != null) {
                holder.itemName.setText(stringItem);
            }
        }
 
        return view;
 
    }
 
    /**
     * Static class used to avoid the calling of "findViewById" every time the getView() method is called,
     * because this can impact to your application performance when your list is too big. The class is static so it
     * cache all the things inside once it's created.
     */
    private static class ViewHolder {
 
        protected TextView itemName;
 
    }
}