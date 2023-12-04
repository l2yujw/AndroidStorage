package com.ryu.androidstorage.layouts

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import androidx.core.app.RemoteInput
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.os.Vibrator
import android.os.VibratorManager
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.ryu.androidstorage.R
import kotlin.concurrent.thread

class Dialog : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        val toast = Toast.makeText(this, "종료하려면 한 번 더 누르세요", Toast.LENGTH_SHORT)
        toast.show()
        toast.addCallback(
            object : Toast.Callback(){
                override fun onToastHidden(){
                    super.onToastHidden()
                }//화면에서 사라지는 순간 실행

                override fun onToastShown() {
                    super.onToastShown()
                }//화면에서 뜨는 순간 실행
            }
        )

        DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

            }
        }, 2020, 8, 21).show()//달력 실행

        TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener{
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

            }

        }, 15, 0, true).show()//시계 실행


        val eventHandler = object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                if(which == DialogInterface.BUTTON_POSITIVE){
                }
                else{
                }
            }
        }//다이얼로그의 버튼 입력을 확인

        AlertDialog.Builder(this).run {
            setTitle("test dialog")
            setIcon(android.R.drawable.sym_def_app_icon)
            setMessage("정말 종료하시겠습니까?")
            setPositiveButton("OK", eventHandler)
            setNegativeButton("Cancel", null)
            setNeutralButton("More", null)
            setPositiveButton("Yes", null)
            setNegativeButton("No", null)
            show()
        }//알림창 띄우기

        val items = arrayOf<String>("사과","복숭아","수박","딸기")
        AlertDialog.Builder(this).run {
            setTitle("items test")
            setIcon(android.R.drawable.sym_def_app_icon)
            setItems(items, object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    items[which]
                }
            })//선택한 아이템 저장

            setMultiChoiceItems(items, booleanArrayOf(true, false, true, false), object : DialogInterface.OnMultiChoiceClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
                    items[which]
                }
            })//체크박스

            setSingleChoiceItems(items, 1, object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                }
            })//라이도 버튼을 포함

            setCancelable(false)//뒤로가기를 했을 때 종료해줄건지 말건지
            setPositiveButton("닫기", null)

            show()
        }.setCanceledOnTouchOutside(false)//알림 바깥 영역을 선택할시 종료해줄건지 말건지


        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val ringtone = RingtoneManager.getRingtone(applicationContext, notification)
        ringtone.play()//소리 얻기

//        val player: MediaPlayer = MediaPlayer.create(this, R.raw.fallbackring) // 음원 재생하기


        val vibrator = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            val vibratorManager = this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }//진동알림


//        알림 빌더
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )

            channel.description = "My Channel One Description"
            channel.setShowBadge(true)
            val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()
            channel.setSound(uri, audioAttributes)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

            manager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(this, channelId)
        }else{
            builder = NotificationCompat.Builder(this)
        }

//        화면 위에서 끌어 내렸을 때 나오는 상태에서의 알림 정보
        builder.setSmallIcon(android.R.drawable.alert_dark_frame)
        builder.setWhen(System.currentTimeMillis())
        builder.setContentTitle("Content Title")
        builder.setContentText("Content Massage")

        manager.notify(11, builder.build())//알림을 띄움
        manager.cancel(11)//취소

        val intent = Intent(this, StopWatch::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 10, intent, PendingIntent.FLAG_IMMUTABLE)
        builder.setContentIntent(pendingIntent) //알림 터치시 해당 어플 실행


//        알림 액션 등록
        val actionIntent = Intent(this, StopWatch::class.java)
        val actionPendingIntent = PendingIntent.getBroadcast(this, 20, actionIntent, PendingIntent.FLAG_IMMUTABLE)
        builder.addAction(
            NotificationCompat.Action.Builder(
                android.R.drawable.sym_def_app_icon,
                "Action",
                actionPendingIntent
            ).build()
        )


//        원격 입력
        val KEY_TEXT_REPLY = "key_text_reply"
        var replyLabel: String = "답장"
        var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
            setLabel(replyLabel)
            build()
        }



//        원격 입력 액션
        val replyIntent = Intent(this, StopWatch::class.java)
        val replyPendingIntent = PendingIntent.getBroadcast(this, 30, replyIntent, PendingIntent.FLAG_IMMUTABLE)
        builder.addAction(
            NotificationCompat.Action.Builder(
                R.drawable.cat,
                "답장",
                replyPendingIntent
            ).addRemoteInput(remoteInput).build()
        )

//        브로드캐스트 리시버에서 사용자가 입력한 글을 받음
        val replytxt = RemoteInput.getResultsFromIntent(intent)
            ?.getCharSequence("key_text_reply")

//        알림 갱신 (알림에 글을 잘 받았다고 신호를 보내는 것)
        manager.notify(11, builder.build())



//        프로그래스 ( 작업이 이루어지는 데 걸리는 시간을 표시)
//        10초 동안 프로그레스 바의 진행값을 증가시키는 예
        builder.setProgress(100, 0, false)
        manager.notify(11, builder.build())

        thread {
            for (i in 1..100){
                builder.setProgress(100, i, false)
                manager.notify(11, builder.build())
                SystemClock.sleep(100)
            }
        }

//        알림 큰 이미지 스타일
        val bigPicture = BitmapFactory.decodeResource(resources, R.drawable.cat)
        val bigStyle = NotificationCompat.BigPictureStyle()
        bigStyle.bigPicture(bigPicture)
        builder.setStyle(bigStyle)

//        알림 긴 텍스트 스타일
        val bigTextStyle = NotificationCompat.BigTextStyle()
        bigTextStyle.bigText(resources.getString(R.string.app_name))
        builder.setStyle(bigTextStyle)

//        알림 상자 스타일
        val style = NotificationCompat.InboxStyle()
        style.addLine("1코스")
        style.addLine("2코스")
        style.addLine("3코스")
        style.addLine("4코스")
        builder.setStyle(style)

    }

}