package com.louiewh.opengl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.louiewh.opengl.databinding.FragmentGlesBinding
import com.louiewh.opengl.render.GlesRender

class GlesFragment: Fragment() {

    private var _binding: FragmentGlesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var mGlesRender: GlesRender? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_GlesFragment_to_SecondFragment)
        }
        val renderName = arguments?.getString("RenderName")
        initGLSurfaceViw(renderName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        // TODO
//        mGlesRender?.destroyShader()
    }

    private fun initGLSurfaceViw(renderName:String?) {
        Log.e("Gles", "initGLSurfaceViw $renderName")
        renderName?.let {
            Log.e("Gles", "initGLSurfaceViw apply $renderName")
            mGlesRender = GlesRender(it).apply {
                this.initShader()
                this.setGlesSurfaceView(binding.glsurfaceview)
            }
        }
    }
}