package com.example.todoapp160421055.view

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.todoapp160421055.R
import com.example.todoapp160421055.databinding.FragmentCreateTodoBinding
import com.example.todoapp160421055.model.Todo
import com.example.todoapp160421055.model.TodoWorker
import com.example.todoapp160421055.util.NotificationHelper
import com.example.todoapp160421055.viewmodel.DetailTodoViewModel
import java.util.concurrent.TimeUnit

class CreateTodoFragment : Fragment(), ButtonAddTodoClickListener,RadioClick {
    private lateinit var binding: FragmentCreateTodoBinding
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding:FragmentCreateTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_todo,container,false)
        return dataBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =ViewModelProvider(this)[DetailTodoViewModel::class.java]
        dataBinding.todo = Todo("","",3,0)
        dataBinding.listener = this
        dataBinding.radioListener = this

//        binding.btnAdd.setOnClickListener {
////            var radio = view.findViewById<RadioButton>(binding.radioGroupPriority.checkedRadioButtonId)
////            var todo = Todo(
////                binding.txtTitle.text.toString(),
////                binding.txtNotes.text.toString(),
////                radio.tag.toString().toInt()
////            )
////
////
////            val list = listOf(todo)
////            viewModel.addTodo(list)
////            val notif = NotificationHelper(view.context, requireActivity())
////            notif.createNotification("Todo Created",
////                "A new todo has been created! Stay focus!")
//
//
//            TodoWorker.activity = requireActivity()
//            val workRequest = OneTimeWorkRequestBuilder<TodoWorker>()
//                .setInitialDelay(30, TimeUnit.SECONDS)
//                .setInputData(
//                    workDataOf(
//                        "title" to "Todo created",
//                        "message" to "Stay focus"
//                    ))
//                .build()
//            WorkManager.getInstance(requireContext()).enqueue(workRequest)
//
//            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
//            Navigation.findNavController(it).popBackStack()
//        }
//
    }
    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,
                                            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode ==NotificationHelper.REQUEST_NOTIF) {
            if(grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED) {
                NotificationHelper(requireContext(), requireActivity())
                    .createNotification("Todo Created",
                        "A new todo has been created! Stay focus!")
            }
        }
    }

    override fun onButtonAddTodo(v: View) {
        val list = listOf(dataBinding.todo)
        viewModel.addTodo(list)
        Toast.makeText(v.context,"Data Added",Toast.LENGTH_LONG).show()
            TodoWorker.activity = requireActivity()
            val workRequest = OneTimeWorkRequestBuilder<TodoWorker>()
                .setInitialDelay(30, TimeUnit.SECONDS)
                .setInputData(
                    workDataOf(
                        "title" to "Todo created",
                        "message" to "Stay focus"
                    ))
                .build()
            WorkManager.getInstance(requireContext()).enqueue(workRequest)
            Navigation.findNavController(v).popBackStack()
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }


}