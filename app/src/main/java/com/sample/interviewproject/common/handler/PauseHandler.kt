/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 15/10/22.
 */

package jp.babyplus.android.common.handler

import android.os.Handler
import android.os.Looper
import android.os.Message
import java.util.*

/**
 * Pause handler.
 */
/**
 * Constructor.
 */
abstract class PauseHandler : Handler(Looper.getMainLooper()) {

    private val messageQueueBuffer = Vector<Message>()

    private var paused: Boolean = false

    /**
     * resume. if messages were stored, all messages will send.
     */
    fun resume() {
        paused = false
        while (messageQueueBuffer.size > 0) {
            val msg = messageQueueBuffer.elementAt(0)
            messageQueueBuffer.removeElementAt(0)
            sendMessage(msg)
        }
    }

    /**
     * pause.
     */
    fun pause() {
        paused = true
    }

    protected abstract fun processMessage(message: Message)

    override fun handleMessage(msg: Message) {
        if (paused) {
            val msgCopy = Message.obtain()
            msgCopy.copyFrom(msg)
            messageQueueBuffer.add(msgCopy)
        } else {
            processMessage(msg)
        }
    }
}