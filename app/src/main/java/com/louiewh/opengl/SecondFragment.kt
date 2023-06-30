package com.louiewh.opengl

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.louiewh.opengl.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var renderAdapter:RenderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("RenderName", GlesRenderConst.renderArray[renderAdapter.getRenderSelect()])
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment, bundle)
        }

        renderAdapter =  RenderAdapter(this.requireContext(), GlesRenderConst.renderArray)
        renderAdapter.setOnItemClickListener(object :RenderAdapter.OnRenderItemClickListener{
            override fun onRenderItemClick(view: View?, position: Int) {
                renderAdapter.setRenderSelect(position)
                renderAdapter.notifyDataSetChanged()
            }
        })
        binding.recycleView.adapter = renderAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}