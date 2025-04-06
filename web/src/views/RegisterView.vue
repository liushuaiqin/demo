<template>
  <div class="register-container">
    <div class="register-box">
      <div class="title-container">
        <h3 class="title">新規登録</h3>
      </div>
      <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" class="register-form" :size="formSize" status-icon >
        <el-form-item prop="name">
          <el-form-item label="名前"/>
          <el-input v-model="ruleForm.name"/>
        </el-form-item>

        <el-form-item prop="age">
          <el-form-item label="年齢"/>
          <el-input v-model.number="ruleForm.age"/>
        </el-form-item>

        <el-form-item prop="status">
          <el-form-item label="ステータス"/>
          <el-select v-model="ruleForm.status">
            <el-option label="有効" :value="1"/>
            <el-option label="無効" :value="0"/>
          </el-select>
        </el-form-item>

        <el-form-item prop="sex">
          <el-form-item label="性別"/>
          <el-select v-model="ruleForm.sex" >
            <el-option label="男性" value="male"/>
            <el-option label="女性" value="female"/>
          </el-select>
        </el-form-item>

        <el-form-item prop="address">
          <el-form-item label="住所"/>
          <el-input v-model="ruleForm.address" type="textarea" :rows="2" />
        </el-form-item>

        <el-form-item prop="phone">
          <el-form-item label="電話番号"/>
          <el-input v-model="ruleForm.phone" />
        </el-form-item>

        <el-form-item prop="password">
          <el-form-item label="パスワード"/>
          <el-input v-model="ruleForm.password" show-password/>
        </el-form-item>

        <el-form-item class="btn-group">
          <el-button
              type="primary"
              @click="submitForm(ruleFormRef)"
              class="register-btn"
          >
            登録
          </el-button>
          <el-button @click="goLogin" class="login-btn">戻る</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import {userAddService} from "@/api/logininfo";

const formSize = ref('default')
const ruleFormRef = ref()
const ruleForm = reactive({
  name: '',
  age: null,
  status: null,
  sex: null,
  address: '',
  phone: '',
  password: ''
})

const router = useRouter()

const rules = reactive({
  name: [
    { required: true, message: '名前を入力してください', trigger: 'blur' },
    { min: 2, max: 10, message: '2-10文字で入力してください', trigger: 'blur' }
  ],
  age: [
    { required: true, message: '年齢を入力してください', trigger: 'blur' },
    { type: 'integer', message: '年齢は数字で入力してください' },
    { min: 18, max: 120, type: 'integer', message: '年齢は18-120の範囲で入力してください' }
  ],
  status: [
    { required: true, message: 'ステータスを選択してください', trigger: 'change' }
  ],
  sex: [
    { required: true, message: '性別を選択してください', trigger: 'change' }
  ],
  password: [
    { required: true, message: 'パスワードを入力してください', trigger: 'blur' },
    { min: 8, max: 20, message: '8-20文字で入力してください', trigger: 'blur' },
    {
      pattern: /^(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[a-z\d@$!%*?&]+$/,
      message: 'パスワードは数字、特殊文字を含む必要があります'
    }
  ]
})

const submitForm = async (formEl) => {
  if (!formEl) return
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      try {
        await userAddService(ruleForm)
        ElMessage.success('登録成功！')
        await router.push('/')
      } catch (error) {
        ElMessage.error('登録に失敗しました')
      }
    } else {
      console.log('error submit!', fields)
    }
  })
}

const goLogin = () => {
  router.push('/')
}
</script>

<style scoped>
.register-container {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
}

.register-box {
  width: 500px;
  padding: 40px;
  background: rgba(255, 255, 255, 0.95);
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  text-align: center;
}

.title {
  color: #2c3e50;
  font-size: 24px;
  margin-bottom: 35px;
}

.register-form {
  padding: 0 40px;
}


.btn-group {
  margin-top: 30px;
}

.register-btn,
.login-btn {
  width: 48%;
  margin-top: 15px;
}

.login-btn {
  background: #fff;
  color: #409EFF;
  border: 1px solid #dcdfe6;
}
</style>