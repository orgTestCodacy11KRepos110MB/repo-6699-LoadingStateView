/*
 * Copyright (c) 2019. Dylan Cai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dylanc.loadingstateview.sample.kotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.dylanc.loadingstateview.LoadingState
import com.dylanc.loadingstateview.LoadingStateImpl
import com.dylanc.loadingstateview.OnReloadListener
import com.dylanc.viewbinding.base.ViewBindingUtil

abstract class BaseBindingFragment<VB : ViewBinding> : Fragment(),
  LoadingState by LoadingStateImpl(), OnReloadListener {

  lateinit var binding: VB private set

  open val contentView get() = binding.root

  open val isDecorated = true

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = ViewBindingUtil.inflateWithGeneric(this, inflater, container, false)
    return contentView.decorate(this, isDecorated)
  }
}