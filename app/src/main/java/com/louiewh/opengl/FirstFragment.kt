package com.louiewh.opengl

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.louiewh.opengl.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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

        initGLSurfaceViw();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initGLSurfaceViw() {
        // 设置 OpenGL 版本(一定要设置)
        binding.glsurfaceview.setEGLContextClientVersion(2)
        // 设置渲染器(后面会讲，可以理解成画笔)
        binding.glsurfaceview.setRenderer(GLSurfaceViewRender())
        // 设置渲染模式为连续模式(会以 60 fps 的速度刷新)
        binding.glsurfaceview.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
    }
}