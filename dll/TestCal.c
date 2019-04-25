#include <jni.h>
#include "TestCal.h"
#include<stdio.h>
#include<math.h>

JNIEXPORT jint JNICALL Java_TestCal_add (JNIEnv *env, jobject object, jint n1,jint n2) 

{
 
  jint res;
 
  res=n1+n2;
 
  return res;

}

JNIEXPORT jint JNICALL Java_TestCal_sub (JNIEnv *env, jobject object, jint n1,jint n2) 

{
 
  jint res;
 
  res=n1-n2;
 
  return res;

}

