package com.nikolaykul.sebastian.utils.view

import android.view.Menu
import android.view.MenuItem

operator fun Menu.get(position: Int): MenuItem = getItem(position)
