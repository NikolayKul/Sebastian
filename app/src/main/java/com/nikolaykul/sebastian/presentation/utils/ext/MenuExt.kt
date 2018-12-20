package com.nikolaykul.sebastian.presentation.utils.ext

import android.view.Menu
import android.view.MenuItem

operator fun Menu.get(position: Int): MenuItem = getItem(position)
