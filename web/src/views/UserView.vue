<template>
  <el-container class="page-container">
    <el-header class="header">
      <div>
        <h2>ユーザー情報管理表</h2>
      </div>
    </el-header>

    <el-container>
      <!-- サイドナビゲーション -->
      <el-aside width="200px" class="aside">
        <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span>ユーザー管理</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-setting"></i>
            <span>システム設定</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-main>

        <div class="operation-content">
          <el-form :inline="true" :model="formData" ref="formRef">
            <el-form-item prop="name">
              <el-input
                  v-model="formData.name"
                  minlength="1"
                  maxlength="15"
                  placeholder="ユーザー名を入力"
              />
            </el-form-item>
            <el-form-item>
              <el-button
                  type="primary"
                  @click="getUserByName"
                  :icon="Search"
                  :disabled="!formData.name || formData.name.trim() === ''"
              >
                検索
              </el-button>
            </el-form-item>
          </el-form>
        </div>

        <el-card>
          <el-table :data="users" style="width: 100%">
            <el-table-column label="ID" prop="id" width="70px"></el-table-column>
            <el-table-column label="ユーザー名" prop="name" width="150px"></el-table-column>
            <el-table-column label="年齢" prop="age" width="130px"></el-table-column>
            <el-table-column label="ステータス" prop="status" width="100px">
              <template #default="{ row }">
                <span>{{ row.status === 1 ? '有効' : '無効' }}</span>
              </template>
            </el-table-column>
            <el-table-column label="性別" prop="sex" width="100px"></el-table-column>
            <el-table-column label="住所" prop="address" width="150px"></el-table-column>
            <el-table-column label="電話番号" prop="phone" width="200px"></el-table-column>
            <el-table-column label="作成時間" prop="createTime"></el-table-column>
            <el-table-column label="更新時間" prop="updateTime"></el-table-column>
            <el-table-column label="操作" width="100">
              <template #default="{ row }">
                <el-button :icon="Edit" circle plain type="primary" @click="updateEcho(row)"></el-button>
                <el-button :icon="Delete" circle plain type="danger" @click="deleteUser(row)"></el-button>
              </template>
            </el-table-column>
            <template #empty>
              <el-empty description="データがありません"/>
            </template>
          </el-table>
        </el-card>
        <el-pagination v-model:current-page="page" v-model:page-size="pageSize" :page-sizes="[3, 5, 10, 15]"
                       layout="jumper, total, sizes, prev, pager, next" background :total="total"
                       @size-change="onSizeChange"
                       @current-change="onCurrentChange" style="margin-top: 20px; justify-content: flex-end">
        </el-pagination>
      </el-main>
    </el-container>

    <!-- ユーザー編集ドロワー -->
    <el-drawer v-model="dialogVisibleUpdate" title="ユーザー編集" direction="rtl" size="35%">
      <!-- ユーザーフォーム -->
      <el-form :model="userModel" label-width="100px">
        <el-form-item label="名前">
          <el-input v-model="userModel.name" placeholder="名前を入力してください"></el-input>
        </el-form-item>
        <el-form-item label="年齢">
          <el-input v-model="userModel.age" placeholder="年齢を入力してください"></el-input>
        </el-form-item>
        <el-form-item label="ステータス">
          <el-select placeholder="選択してください" v-model="userModel.status">
            <el-option label="有効" value="1"/>
            <el-option label="無効" value="0"/>
          </el-select>
        </el-form-item>
        <el-form-item label="性別">
          <el-select placeholder="選択してください" v-model="userModel.sex">
            <el-option label="男性" value="男"/>
            <el-option label="女性" value="女"/>
          </el-select>
        </el-form-item>
        <el-form-item label="住所">
          <el-input v-model="userModel.address" placeholder="住所を入力してください"></el-input>
        </el-form-item>
        <el-form-item label="電話番号">
          <el-input v-model="userModel.phone" placeholder="電話番号を入力してください"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateUser(); clearUserModel()">更新</el-button>
          <el-button type="info" @click="dialogVisibleUpdate = false">キャンセル</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
  </el-container>

</template>

<script setup>
import {userGetAllService, userUpdateService, userDeleteService, userPageService, userSearchService} from '@/api/user'

import {reactive, ref} from "vue"

import {Delete, Edit, Search} from '@element-plus/icons-vue'

const users = ref([])

const getAllUser = async function () {
  let data = await userGetAllService();
  users.value = data.data;
}
getAllUser()

const userModel = ref({
  id: '',
  name: '',
  age: '',
  status: '',
  sex: '',
  address: '',
  phone: '',
  password: '',
  createTime: '',
  updateTime: ''
})

const dialogVisibleUpdate = ref(false)

const clearUserModel = () => {
  userModel.value.name = '';
  userModel.value.age = '';
  userModel.value.status = '';
  userModel.value.sex = '';
  userModel.value.address = '';
  userModel.value.phone = '';
}

const updateEcho = (row) => {
  dialogVisibleUpdate.value = true
  userModel.value.name = row.name
  userModel.value.age = row.age
  userModel.value.status = row.status.toString();
  userModel.value.sex = row.sex
  userModel.value.address = row.address
  userModel.value.phone = row.phone
  userModel.value.id = row.id
}

// ページングデータモデル
const page = ref(1)
const total = ref(20)
const pageSize = ref(10)


const formData = reactive({
  name: ''
})

// ユーザー名で検索
const getUserByName = async () => {
  let params = {
    name: formData.name.trim()
  }

  let result = await userSearchService(params)
  users.value = result.data
  total.value = result.data.length
}

// 条件付きページング検索
const getUserByCondition = async () => {
  let params = {
    page: page.value,
    pageSize: pageSize.value
  }

  let result = await userPageService(params)

  users.value = result.data.rows

  total.value = result.data.total
}

// ページサイズ変更時の処理
const onSizeChange = (size) => {
  pageSize.value = size
  getUserByCondition()
}
// 現在のページ番号変更時の処理
const onCurrentChange = (num) => {
  page.value = num
  getUserByCondition()
}

import {ElMessage} from 'element-plus'

// ユーザー更新
const updateUser = async () => {
  let result = await userUpdateService(userModel.value)
  ElMessage.success(result.message ? result.message : '更新成功')
  // 再度ユーザー情報を取得
  await getAllUser()
  // 編集ドロワーを閉じる
  dialogVisibleUpdate.value = false
}

// IDでユーザー削除
import {ElMessageBox} from 'element-plus'

const deleteUser = (row) => {
  ElMessageBox.confirm(
      'このユーザー情報を削除してもよろしいですか？',
      '確認',
      {
        confirmButtonText: '確認',
        cancelButtonText: 'キャンセル',
        type: 'warning',
      }
  )
      .then(async () => {
        // ユーザーが確認をクリック
        let result = await userDeleteService(row.id)
        ElMessage.success(result.message ? result.message : '削除しまし')
        // 再度すべてのユーザー情報を取得
        await getAllUser()
      })
      .catch(() => {
        // ユーザーがキャンセルをクリック
        ElMessage({
          type: 'info',
          message: '削除をキャンセルしました',
        })
      })
}
</script>


<style scoped>
.page-container {
  height: 100vh;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
  padding: 0 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .2);
}

.aside {
  background-color: #545c64;
}

.operation-content {
  background-color: #fff;
  padding: 15px 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .2);
  margin-bottom: 20px;
  display: flex;
  gap: 15px;
}

</style>