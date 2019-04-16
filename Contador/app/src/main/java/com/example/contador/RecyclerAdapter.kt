package com.example.contador

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class RecyclerAdapter(private val jovenes: ArrayList<Joven>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var mContext: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val inflatedView = parent.inflate(R.layout.listajovenes_item_row, false)
        mContext = inflatedView.context
        return ViewHolder(inflatedView)
    }

    override fun getItemCount() = jovenes.size

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val joven: Joven = jovenes[position]

        holder.bind(joven)
        holder.itemView.setOnClickListener{
            Log.d(TAG, "Clicked on BindViewHolder")
            holder.onClick(it)
        }
    }

    class ViewHolder(v: View) :
        RecyclerView.ViewHolder(v), View.OnClickListener {

        //2
        private var mJoven: Joven? = null
        private var mNombre: TextView? = null
        private var mDestino: TextView? = null
        private var mFoto: ImageView? = null

        init {
            mNombre = itemView.findViewById(R.id.jovenNombre)
            mDestino = itemView.findViewById(R.id.jovenDestino)
            mFoto = itemView.findViewById(R.id.jovenFoto)
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val context = v.context
            val intent = Intent(context, ListaActivity::class.java)
            intent.putExtra(JOVEN_KEY, mJoven)
            context.startActivity(intent)
            Log.d(TAG, "CLICKED on ViewHolder")
        }

        @SuppressLint("SetTextI18n")
        fun bind(joven: Joven){
            this.mJoven = joven
            mNombre?.text = joven.name + " " + joven.lastName
            mDestino?.text = joven.destination
            if (joven.sex == "Hombre") mFoto?.setImageResource(R.drawable.men) else mFoto?.setImageResource(R.drawable.women)
        }

        companion object {
            private val JOVEN_KEY = "JOVEN"
            private val TAG = "ViewHolder"
        }


    }
}

