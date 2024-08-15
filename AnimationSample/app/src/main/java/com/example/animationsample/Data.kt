package com.example.animationsample

import com.example.animationsample.activity_animation.DialogPopAnimation
import com.example.animationsample.dynamic_anim.ProgressCircleIndicator
import com.example.animationsample.dynamic_anim.ProgressCircleUsingMaterial
import com.example.animationsample.icon_anim.SimpleIconAnimation
import com.example.animationsample.list_anim.Carousel
import com.example.animationsample.list_anim.DragListAnimation
import com.example.animationsample.list_anim.LoopCarousel
import com.example.animationsample.list_anim.SwipeListAnimation
import com.example.animationsample.list_anim.SwipeListWithBackgroundAnimation
import com.example.animationsample.list_anim.TopicAndList
import com.example.animationsample.object_anim.BounceAnimation
import com.example.animationsample.object_anim.ButtonColorActivity
import com.example.animationsample.object_anim.ButtonRotationAnimation
import com.example.animationsample.object_anim.ButtonShrinkActivity
import com.example.animationsample.object_anim.ChangeShapeAnimation
import com.example.animationsample.object_anim.InterpolatorActivity
import com.example.animationsample.object_anim.NumberCountAnimation
import com.example.animationsample.object_anim.PageIndicatorAnimationActivity
import com.example.animationsample.tp_anim.ExpressBookingIndicatorActivity
import com.example.animationsample.tp_anim.ExpressLoadActivity
import com.example.animationsample.tp_anim.SampleAMLoginActivity
import com.example.animationsample.tp_anim.SampleAMSplashActivity
import com.google.android.material.progressindicator.CircularProgressIndicator

data class AnimationData(
    var title: String = "",
    var pageType: Int = 0,
    var page: Any = "",
    var type: Int = 0
)

val animationListData = mutableListOf<AnimationData>(
    AnimationData("Object Animation", -1, "", 0),
    AnimationData("Size Animation", 0, ButtonShrinkActivity(), 0),
    AnimationData("Color Animation", 0, ButtonColorActivity(), 0),
    AnimationData("Rotation Animation", 0, ButtonRotationAnimation(), 0),
    AnimationData("Shape Animation", 0, ChangeShapeAnimation(), 0),
    AnimationData("Bounce Animation", 0, BounceAnimation(), 0),
    AnimationData("Interpolator",0,InterpolatorActivity(),0),
    AnimationData("Number Count Animation", 0, NumberCountAnimation(), 0),
    AnimationData("Page Indicator Animation", 0, PageIndicatorAnimationActivity(), 0),
    AnimationData("Icon Animation", -1, "", 0),
    AnimationData("Simple Icon Animation", 0, SimpleIconAnimation(), 0),
    AnimationData("List Animation", -1, "", 0),
    AnimationData("Drag List Animation", 0, DragListAnimation(), 0),
    AnimationData("Swipe List Animation", 0, SwipeListAnimation(), 0),
    AnimationData("Swipe List Animation with Background", 0, SwipeListWithBackgroundAnimation(), 0),
    AnimationData("Topic And Text List", 0, TopicAndList(), 0),
    AnimationData("Carousel", 0, Carousel(), 0),
    AnimationData("Loop Carousel", 0, LoopCarousel(), 0),
    AnimationData("Activity Animation", -1, "", 0),
    AnimationData("Dialog Pop", 0, DialogPopAnimation(), 0),
    AnimationData("Dynamic Animation", -1, "", 0),
    AnimationData("Circle Progress indicator",0,ProgressCircleIndicator(),0),
    AnimationData("Circle Progress (Using Material Component)",0,ProgressCircleUsingMaterial(),0),
    AnimationData("Past TestProject Animation", -1, "", 0),
    AnimationData("Sample ModuleC Login", 0, SampleAMLoginActivity(), 0),
    AnimationData("Sample ModuleC Splash", 0, SampleAMSplashActivity(), 0),
    AnimationData("Express Booking Indicator", 0, ExpressBookingIndicatorActivity(), 0),
    AnimationData("Express Load", 0, ExpressLoadActivity(), 0),

    )

val colorListData = listOf<Int>(
    R.color.color1,
    R.color.color2,
    R.color.color3,
    R.color.color4,
)


