<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginTop="10dp"
        android:id="@+id/heading"
        android:text="@string/using_view_flipper"
        android:textAppearance="@style/TextAppearance.AppCompat.Medium"
        android:textStyle="bold"
        android:textSize="20sp"
        android:textColor="@color/black"/>

    <ViewFlipper
        android:layout_width="match_parent"
        android:layout_height="400dp"
        app:layout_constraintVertical_weight="0"
        app:layout_constraintTop_toBottomOf="@id/heading"
        android:id="@+id/viewFlipper"
        android:padding="16dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:orientation="vertical"
            android:background="@android:color/holo_green_dark"
            android:padding="10dp"
            android:layout_height="match_parent">

            <TextView
                android:layout_width="wrap_content"
                android:textSize="24sp"
                android:textAppearance="@style/TextAppearance.AppCompat.Medium"
                android:textColor="@color/white"
                android:layout_height="wrap_content"
                android:text="@string/first_screen"/>

            <Button
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:id="@+id/nextButton"
                android:text="@string/next"
                />

        </LinearLayout>

        <LinearLa