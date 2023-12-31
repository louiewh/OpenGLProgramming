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
open class FragmentShader : Fragment() {

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
            findNavController().navigate(R.id.action_global_FragmentShaderList)
        }
        val renderName = arguments?.getString("RenderName")
        initGLSurfaceViw(renderName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mGlesRender?.destroyShader()
    }

    private fun initGLSurfaceViw(renderName:String?) {
        Log.e("Gles", "initGLSurfaceViw $renderName")
        renderName?.let {
            Log.e("Gles", "initGLSurfaceViw apply $renderName")
            mGlesRender = GlesRender(it).apply {
                this.initShader()
                this.setGLSurfaceView(binding.glsurfaceview)
            }
        }
    }
}