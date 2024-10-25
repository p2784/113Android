from flask import Flask
app = Flask(__name__)


from flask import request, abort
from linebot import LineBotApi, WebhookHandler
from linebot.exceptions import InvalidSignatureError
from linebot.models import MessageEvent, TextMessage, PostbackEvent, TextSendMessage, TemplateSendMessage, ConfirmTemplate, MessageTemplateAction, ButtonsTemplate, PostbackTemplateAction, URITemplateAction
, CarouselTemplate , CarouselColumn, ImageCarouselTemplate, ImageCarouselColumn
from urllib.parse import parse_qsl

line_bot_api = LineBotApi('yQgWt5ZS3FO65qpFse1qT4Igj2Pz0bGyr3RwwbHyrdUGGAM0zjZYrASaUalY4tZW7w/4Ap7xjJQY6VFZGfwxsxXZdFWEBsVFoFQoKR7IXHeSYlgcpCF1z9uFD1bS3wJxh1Tb1OSIgdB04t89/1O/w1cDnyilFU=')
handler = WebhookHandler('')

@app.route('/callback', methods=['POST'])
def callback():
    signature = request.headers['X-Line-Signature']
    body = request.get_data(as_text=True)
    try:
        handler.handle(body, signature)
    except InvalidSignatureError:
        abort(400)
    return 'OK'

@handler.add(MessageEvent, message=TextMessage)
def handle_message(event):
    mtext = event.message.text
    if mtext =='@按鈕樣板':
        sendButton(event)
    
    elif mtext =='@確認樣板':
        sendConfirm(event)
    
    elif mtext =='@轉盤樣板':
        sendCarousel(event)
    
    elif mtext =='@圖片轉盤':
        sendImgCarousel(event)
        
    elif mtext =='@購買披薩':
        sendPizza(event)
        
    elif mtext == '@yes':
        sendYes(event)

@handler.add(PostbackEvent)
def handle_postback(event):
    backdata = dict(parse_qsl(event.postback.data))
    if backdata.get('action') == 'buy':
        sendBack_buy(event,backdata)
    elif backdata.get('action') == 'sell':
        sendBack_sell(event, backdata)

def sendButton(event):
    try:
        message = TemplateSendMessage(
            alt_text='按鈕樣板',
            template=ButtonsTemplate(
                thumbnail_image_url='https://i.imgur.com/eTldj2E.png',
                title='按鈕樣板示範',
                text='請選擇',
                actions=[
                    MessageTemplateAction(
                        label='文字訊息',
                        text='購買披薩'
                    ),
                    URITemplateAction(
                        label='連結網頁',
                        uri='http://www.zashare.org'
                    ),
                    PostbackTemplateAction(
                        label='回傳訊息',
                        text='@購買披薩',
                        data='action=buy'
                    ),
                ]
            )
        )
        line_bot_api.reply_message(event.reply_token,message)
    except:
        line_bot_api.reply_message(event.reply_token, TextSendMessage(text='發生錯誤！'))

def sendConfirm(event):
    try:
        message = TemplateSendMessage(
            alt_text='確認樣板',
            template=ConfirmTemplate(
                text='你確定要購買這項商品嗎?',
                actions=[
                    MessageTemplateAction(
                        label='是',
                        text='@yes'
                    ),
                    MessageTemplateAction(
                        label='否',
                        text='@no'
                    )
                ]
            )
        )
        line_bot_api.reply_message(event.reply_token, message)
    except:
        line_bot_api.reply_message(event.reply_token, TextSendMessage(text='發生錯誤！'))

def sendCarousel(event): 
    try:
        message = TemplateSendMessage(
            alt_text='轉盤樣板',
            template=CarouselTemplate(
                columns=[
                    CarouselColumn(
                        thumbnail_image_url='http://i.imgur.com/4QfKuz1.png',
                        title='這是樣板一',
                        text='第一個轉盤樣板',
                        actions=[
                            MessageTemplateAction(
                                label='文字訊息一',
                                text='賣披薩'
                            ),
                            URITemplateAction(
                                label='連結文淵閣網頁',
                                uri='http://www.e-happy.com.tw'
                            ),
                            PostbackTemplateAction(
                                label='回傳訊息--',
                                data='action=sell&item=披薩'
                            ),
                        ]
                    ),
                    CarouselColumn(
                        thumbnail_image_url='http://i.imgur.com/qaAdBkR.png',
                        title='這是樣板二',
                        text='第二個轉盤樣板',
                        actions=[
                            MessageTemplateAction(
                                label='文字訊息二',
                                text='賣飲料'
                            ),
                            URITemplateAction(
                                label='連結台大網頁',
                                uri='http://www.ntu.edu.tw'
                                
                            ),
                            PostbackTemplateAction(
                                label='回傳訊息二',
                                data='action=sell&item=飲料'
                            ),
                        ]
                    )
                ]
            )
        )
        line_bot_api.reply_message(event.reply_token,message)
    except:
        line_bot_api.reply_message(event.reply_token,TextSendMessage(text='發生錯誤!'))
        
def sendImgCarousel(event):  # 傳送圖片轉盤
    try:
        message = TemplateSendMessage(
            alt_text='圖片轉盤樣板',  # 當使用者裝置無法顯示圖片時的替代文字
            template=ImageCarouselTemplate(
                columns=[
                    ImageCarouselColumn(
                        image_url='https://i.imgur.com/4QfKuz1.png',
                        action=MessageTemplateAction(
                            label='文字訊息',
                            text='賣披薩'
                        )
                    ),
                    ImageCarouselColumn(
                        image_url='https://i.imgur.com/qaAdBkR.png',
                        action=PostbackTemplateAction(
                            label='回傳訊息',
                            data='action=sell&item=飲料'
                        )
                    )
                ]
            )
        )
        line_bot_api.reply_message(event.reply_token,message)
    except:
        line_bot_api.reply_message(event.reply_token, TextSendMessage(text="發生錯誤！"))

def sendPizza(event):  # 傳送購買披薩訊息
    try:
        message = TextSendMessage(
            text='感謝您購買披薩，我們將盡快為您製作。'
        )
        line_bot_api.reply_message(event.reply_token, message)
    except:
        line_bot_api.reply_message(event.reply_token,TextSendMessage(text="發生錯誤！"))

def sendYes(event):  # 傳送感謝購買訊息
    try:
        message = TextSendMessage(
            text='感謝您的購買，\n我們將盡快寄出商品。'
            )
        line_bot_api.reply_message(event.reply_token, message)
    except Exception as e:
        print("發生錯誤:", e)
        line_bot_api.reply_message(event.reply_token, TextSendMessage(text="發生錯誤！"))



def sendBack_buy(event, backdata):  # 處理購買 Postback 事件
    try:
        # 組合回覆訊息
        text1 = '感謝您購買披薩，我們將盡快為您製作。\n(action 的值為: ' + backdata.get('action') + ')'
        text1 += '\n(可將處理程式寫在此處。)'
        # 建立文字訊息物件
        message = TextSendMessage(
            text=text1
            )
        # 回覆訊息給使用者
        line_bot_api.reply_message(event.reply_token, message)
    except Exception as e:  # 捕捉所有例外並印出錯誤訊息
        print('發生錯誤:', e)
        line_bot_api.reply_message(event.reply_token, TextSendMessage(text='發生錯誤！'))

def sendBack_sell(event, backdata):  # 處理銷售 Postback 事件
    try:
        # 建立文字訊息物件
        message = TextSendMessage(
        # 組合回覆訊息
            text = '點選的是賣' + backdata.get('item')
        )
        # 回覆訊息給使用者
        line_bot_api.reply_message(event.reply_token, message)
    except:
        line_bot_api.reply_message(event.reply_token, TextSendMessage(text='發生錯誤！'))

if __name__ == '__main__':
    app.run()
