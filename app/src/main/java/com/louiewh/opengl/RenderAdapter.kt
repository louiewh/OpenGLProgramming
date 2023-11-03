package com.louiewh.opengl

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RenderAdapter(private val context:Context, private val renderArray:Array<GlesConst.ShaderMeta>) : RecyclerView.Adapter<RenderAdapter.RenderViewHolder>(){
    companion object {
        private var mSelectIndex = 0
    }

    private var mOnItemClickListener: OnRenderItemClickListener? = null

    fun setOnItemClickListener(listener:OnRenderItemClickListener){
        mOnItemClickListener = listener
    }

    fun setRenderSelect(index:Int){
        mSelectIndex = index
    }

    fun getRenderSelect():Int{
        return mSelectIndex
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RenderViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.render_item_layout, parent, false)
        return RenderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return renderArray.size
    }

    override fun onBindViewHolder(holder: RenderViewHolder, position: Int) {
        holder.mTitle.text = renderArray[position].renderName
        if (position == mSelectIndex) {
            holder.mRadioButton.isChecked = true
            holder.mTitle.setTextColor(context.resources.getColor(R.color.black, null))
        } else {
            holder.mRadioButton.isChecked = false
            holder.mTitle.text = renderArray[position].renderName
            holder.mTitle.setTextColor(Color.GRAY)
        }
        holder.itemView.tag = position
        holder.itemView.setOnClickListener {
            mOnItemClickListener?.onRenderItemClick(
                holder.itemView,
                position
            )
        }
    }

    class RenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mRadioButton: RadioButton
        var mTitle: TextView

        init {
            mRadioButton = itemView.findViewById(R.id.radio_button)
            mTitle = itemView.findViewById(R.id.render_type)
        }
    }

    interface OnRenderItemClickListener {
        fun onRenderItemClick(view: View?, position: Int)
    }
}