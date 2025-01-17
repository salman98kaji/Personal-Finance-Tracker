
import { createSlice } from "@reduxjs/toolkit";

const accountSlice = createSlice({
    name:"accounts",
    initialState:[],
    reducers: {
        setAccounts:(state, action) => {
            return action.payload;// Set new accounts data
        },
        clearAccounts:() => {
            return [];// Reset accounts state
        }
    }
});

export const {setAccounts, clearAccounts} = accountSlice.actions;
export default accountSlice.reducer;