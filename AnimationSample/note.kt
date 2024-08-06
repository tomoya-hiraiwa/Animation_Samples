/*
          * How to distinguish between ValueAnimator and ObjectAnimator
          * 1.ObjectAnimator can change view attribute value like X,Y,translation,rotation.
          * 2.ValueAnimator is only animate numeric value.
          *
          * When should use Value Animator?
          * case1: Can't change attribute value using ObjectAnimator.
          * case2: Change multiple attribute value in same value.
          *
          *Other case, we have to use ObjectAnimator.
           */