package com.louiewh.opengl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.louiewh.opengl.databinding.FragmentFirstBinding
import com.louiewh.opengl.render.GlesRender

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var mGlesRender:GlesRender? = null;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        val render = arguments?.getString("Render")
        initGLSurfaceViw(render)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mGlesRender?.destroyShader()
    }

    private fun initGLSurfaceViw(render:String?) {
        Log.e("Gles", "initGLSurfaceViw $render")
        render?.let {
            Log.e("Gles", "initGLSurfaceViw apply $render")
            mGlesRender = GlesRender(it).apply {
                this.initShader()
                this.setGLSurfaceView(binding.glsurfaceview)
            }
        }
    }
}