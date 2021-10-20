package com.shady.recycleviewapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FragmentList: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fillButton: Button
    private var data = mutableListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_fragment, container, false)

        recyclerView = view.findViewById(R.id.rvRecycleView)
        fillButton = view.findViewById(R.id.btnFill)

        fillButton.setOnClickListener(){
            //data += user
            for (i in 1..100) {
                val user = User(
                    fName = "fName $i",
                    lName = "lName $i",
                    id = i,
                    score = i * 2
                )
                data += user
            }
                //val rvAdaptorData = appDB.userDao.getAllUsers()

                    recyclerView.adapter = UserRecycleViewAdapter(data)

            }
        recyclerView.layoutManager = LinearLayoutManager(context)


        return view
        }


    class UserRecycleViewAdapter(private val userList: List<User>) : RecyclerView.Adapter<UserAdapter>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item, parent,false)
            return UserAdapter(view)
        }

        override fun onBindViewHolder(holder: UserAdapter, position: Int) {
            val user = userList[position]
            holder.idTextView.text = user.id.toString()
            holder.nameTextView.text = "${user.fName} ${user.lName}"
            holder.scoreTextView.text = user.score.toString()
            holder.itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val detailsFragment = DetailsFragment()
                    val activity = v!!.context as AppCompatActivity
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,detailsFragment).commit()
                }
            })
        }

        override fun getItemCount(): Int {
            return userList.size
        }
    }

    class UserAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.tvId)
        val nameTextView: TextView = itemView.findViewById(R.id.tvName)
        val scoreTextView: TextView = itemView.findViewById(R.id.tvScore)
    }
}
