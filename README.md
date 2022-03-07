# Dojo Android 2022

## 事前課題

### mixiトレーニング

http://mixi-inc.github.io/AndroidTraining/

数年前の資料となるため、読み物としてのみ学習してください。（セットアップや実際のコード部分は最新の公式ドキュメントなどを参考にして下さい）

- 1.1 Android-OSについて
- 1.4 Androidの基礎知識
- 1.5 Androidのビルドについて
- 2.1 アプリのレイアウト作成
- 2.2 ActivityとFragment
- 2.3 アプリのリソース管理
- 2.4 メッセージングと通知
- 2.7 直列化とコレクション、永続化
- 2.8 非同期処理
- 2.9 ネットワーク通信
    - 「ネットワークの接続状態を知る 」まで

### Android Kotlin の基礎のための Codelab

https://developer.android.com/courses/kotlin-android-fundamentals/toc?hl=ja

- 必修
    - 1.0-4.2
    - 7.1-7.5
    - 8.1-8.3
    - 10.1-10.3
- 任意
    - それ以外

### Codelab

https://developer.android.com/courses/pathways/android-basics-kotlin-one?hl=ja#0

こちらは任意ですが、実践的なので実施を強くお勧めします

## 課題 - あんどっち

### アプリ概要

- 立派なAndroidエンジニアを目指して、あなただけのあんどっちを育てよう！
- [android-developer-roadmap2022](https://github.com/skydoves/android-developer-roadmap)
  の学習パスを通過し、ノード(スキル)を習得していきましょう！
    - このロードマップからすべてを学ぶ必要はありません。ですから、あなたにとって有益なセクションだけを読むことをお勧めします
- あなたがノード(スキル)を習得することで、あんどっちも一緒にレベルが上がり、成長させていくことができます

### 画面仕様

#### ノード一覧画面

android-developer-roadmap2022にある全スキルを確認できる画面です。

- android-developer-roadmap2022.jsonをパースし、画面に表示させてください

#### あんどっち表示画面

あなたのあんどっちの状態を知ることができる画面です。

- `1`から始まるレべル情報を画面に表示させてください
- あなたのあんどっちの名前を表示できるようにしてください
- あなたのあんどっちの名前を変更できるようにしてください
    - 名前はSharedPreferencesに保存&参照ができるようにしてください
- あんどっちの特定の画像リソースを後述するレベルの仕様に応じて画面に表示させてください

#### ノード習得画面

android-developer-roadmap2022にあるノードのうち、特定のノードを習得できる画面です。

- あんどっち表示画面にダイアログ形式で被せて表示しても良いですし、ノード一覧画面に選択できる機能を持たせても問題ありません
- 特定のノードを選択することで、習得できるようにしてください。
- 習得したノードはSharedPreferencesに保存&参照ができるようにしてください

### 機能仕様

#### 習得

- 各ノード情報はタップ処理を行えるようにしてください
- 各ノード情報はタップされた場合、習得したものと見なされます。タップした際はそのノードが習得されたことがわかるようにしてください(背景色や選択状態の表示を想定)

#### レベル

- レベルは以下の条件で1レベルづつ上がっていくものとします
    - 条件
        - 取得したノード情報が2つ増えた場合
        - 1レベル → ノード習得0個
        - 2レベル → ノード習得2個
        - 3レベル → ノード習得4個
        - 4レベル → ノード習得6個
        - ...
- レベルが上がるのは、その対象となるノード情報を習得したタイミングと同じになるようにしてください
- レベルが上がった場合は、レベルが上がったことがわかるようにしてください
- あんどっちは特定のレベルに応じて画像リソースを差し替え、成長を表現してください
- あんどっちの画像リソースは以下の条件で差し替えるものとします
    - 条件
        - 5レベル
        - 10レベル
        - 15レベル
        - 20レベル

### 補足事項

- あんどっちの画像リソースとandroid-developer-roadmap2022.jsonについては、本リポジトリのassetsディレクトリ内に格納されているものを使用してください
- レイアウト作成は基本xml形式でお願いします。理由として、業務に必要なスキルとしてまずはxmlの知識を習得してもらいたいためです
    - メンターとメンティーですり合わせてもらってJetpackComposeを選択してもらっても良いですが、xmlをある程度使えることを前提とします
- 開発言語としては、Kotlinを使用してください
- Androidロボット(Droidくん)を使用しているため、以下のライセンス情報をアプリ内で表示してください
    - `The Android robot is reproduced or modified from work created and shared by Google and used according to terms described in the Creative Commons 3.0 Attribution License.`

## 🌟以下、中級者向けの追加の要件・仕様🌟

### 画面仕様

#### ノード一覧画面

- android-developer-roadmap2022.jsonをリモートサーバーからデータを取得し、画面に表示させてください

#### ノード習得画面

- 各ノード情報を習得した状態から再度タップした場合、習得情報を取り消せるようにしてください

### 機能仕様

#### 習得

- SharedPreferencesに保存している習得済みのノードを以下のいずれかの方法にリプレイスしてください(両方対応しても問題ありません)
    - 各ノードの習得状況を保存する処理をRoom、またはSQLDelightを使用した方法で実装してください
    - 各ノードの習得状況を保存する処理をリモードサーバー経由で行えるようにしてください
- 習得アクションを行う際、対象のノードに対してメモを残せるようにしてください
- 習得アクションを行った際、あんどっちが動くアニメーションを自由な発想で追加してください
- ノードの習得優先度に応じて、各ノードの表示形式を自由な発想でデザインしてください

#### レベル

- あんどっちが特定のレベルに応じて成長する際、あんどっちが成長するアニメーションを自由な発想で追加してください

### その他

- android-developer-roadmap2022.jsonの情報に加えて、あなたが習得したAndroid以外の技術をノード情報として自由に追加・削除・編集できるようにしてくだい
- 作成したxml形式のレイアウトをJetpackComposeを用いた実装に変更してください