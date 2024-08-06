package com.example.animationsample

import com.example.animationsample.icon_anim.SimpleIconAnimation
import com.example.animationsample.list_anim.DragListAnimation
import com.example.animationsample.list_anim.SwipeListAnimation
import com.example.animationsample.object_anim.BounceAnimation
import com.example.animationsample.object_anim.ButtonColorActivity
import com.example.animationsample.object_anim.ButtonRotationAnimation
import com.example.animationsample.object_anim.ButtonShrinkActivity
import com.example.animationsample.object_anim.ChangeShapeAnimation
import com.example.animationsample.tp_anim.SampleAMLoginActivity
import com.example.animationsample.tp_anim.SampleAMSplashActivity

data class AnimationData(var title: String = "",var pageType: Int = 0,var page: Any = "",var type: Int = 0)

val animationListData = mutableListOf<AnimationData>(
    AnimationData("Object Animation",-1,"",0),
    AnimationData("Size Animation",0, ButtonShrinkActivity(),0),
    AnimationData("Color Animation",0, ButtonColorActivity(),0),
    AnimationData("Rotation Animation",0, ButtonRotationAnimation(),0),
    AnimationData("Shape Animation",0, ChangeShapeAnimation(),0),
    AnimationData("Bounce Animation",0, BounceAnimation(),0),
    AnimationData("Icon Animation",-1,"",0),
    AnimationData("Simple Icon Animation",0, SimpleIconAnimation(),0),
    AnimationData("List Animation",-1,"",0),
    AnimationData("Drag List Animation",0, DragListAnimation(),0),
    AnimationData("Swipe List Animation",0, SwipeListAnimation(),0),
    AnimationData("Past TestProject Animation",-1,"",0),
    AnimationData("Sample ModuleC Login",0, SampleAMLoginActivity(),0),
    AnimationData("Sample ModuleC Splash",0, SampleAMSplashActivity(),0),

    )

val colorListData = listOf<Int>(
    R.color.color1,
    R.color.color2,
    R.color.color3,
    R.color.color4,
)


