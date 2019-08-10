package com.cubesoft.oleksandr.havryliuk.trueth_or_dare

interface Navigation {

    interface Navigator {

        fun openInfoActivity()

        fun openEditPlayerActivity()

        fun exit()
    }

    interface Controller {

        fun OnInfo()

        fun OnEditPlayerClick()

        fun OnExiClick()
    }
}