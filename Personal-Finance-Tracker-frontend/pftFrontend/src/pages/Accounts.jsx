import { useEffect, useState } from 'react'
import axiosInstance from '../utils/axiosInstance';

function Accounts() {

  const [accounts, setAccounts] = useState([]);
  const [form, setForm] = useState({ accountName:"",accountType:"SAVINGS", balance:0 });
  const [editingId, setEditingId] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  //fetch accounts from the backend
  const fetchAccounts = async () => {
    setLoading(true);

    try {
      const response = await axiosInstance.get("/accounts");
      setAccounts(response.data);
      setError("");

    } catch (error) {
      console.error("Error feetching accounts", error);
      setError("Failed to fetch accounts. Please try again later.");

    } finally {
      setLoading(false);
    }
  };

  // Fetch accounts on component mount
  useEffect(() => {
    fetchAccounts();
  },[]);

  // Handle form input changes
  const handleInputChange = (e) => {
    const {name, value} = e.target;
    setForm({...form,[name]:value});
  };

  // Add or update an account
  const handleAddOrUpdateAccount = async () => {
    try {
      if(editingId) {
        //update the account
        await axiosInstance.put(`/accounts/${editingId}`, form);
        setAccounts((prev) => prev.map((acc) => (acc.accountId == editingId ? {...acc, ...form} : acc)));
      } else {
        //Add new account
        const response = await axiosInstance.post("/accounts", form);
        setAccounts([...accounts, response.data]);
      }
      setForm({ accountName: "", accountType: "SAVINGS", balance: 0 });
      setEditingId(null);
      setError("");
    } catch (error) {
      console.error("Error saving account:", error);
    setError("Failed to save account. Please try again.");
    }
  };

  const handleEditAccount = (accountId) => {
    console.log("Editing Account with ID:", accountId);
    const account = accounts.find((acc) => acc.accountId === accountId);
    if (account) {
      setForm({
        accountName: account.accountName,
        accountType: account.accountType,
        balance: account.balance
      });
      setEditingId(accountId); // Ensure editingId is properly set
    }
  };

  const handleDeleteAccount = async (accountId) => {
    try {
      await axiosInstance.delete(`/accounts/${accountId}`);
      setAccounts(accounts.filter((acc) => acc.accountId !== accountId));
      setError("");
    } catch (error) {
      console.error("Error deleting account:", error);
      setError("Failed to delete account. Please try again.");
    }
  }

  return (
    <div className='container mx-auto items-center justify-center '>
      <h2 className='text-2xl font-bold mb-6'>Accounts</h2>
      {error && <div className='text-red-500 mb-4'>{error}</div>}
      <div className="container mx-auto px-4">
        <h3 className="text-xl font-semibold mb-4">Your Accounts</h3>
        {loading ? (<p>Loading Accounts....</p>) : (
          <table className="w-3/4 mx-auto border-collapse border border-gray-300">
            <thead>
              <tr className="bg-gray-200">
                <th className="border border-gray-300 px-4 py-2">Account Name</th>
                <th className="border border-gray-300 px-4 py-2">Type</th>
                <th className="border border-gray-300 px-4 py-2">Balance</th>
                <th className="border border-gray-300 px-4 py-2">Actions</th>
              </tr>
            </thead>
            <tbody>
              {accounts.map((account) => (
                <tr key={account.accountId}>
                  <td className="border border-gray-300 px-4 py-2">{account.accountName}</td>
                  <td className="border border-gray-300 px-4 py-2">{account.accountType}</td>
                  <td className="border border-gray-300 px-4 py-2">{account.balance}</td>
                  <td className="border border-gray-300 px-4 py-2">
                    <button 
                      className='bg-green-400 px-4 py-1 rounded hover:bg-blue-700 mr-2'
                      onClick={() => handleEditAccount(account.accountId)}
                    >Edit
                    </button>
                    <button 
                      className='bg-green-400 px-2 py-1 rounded hover:bg-red-700'
                      onClick={() => handleDeleteAccount(account.accountId)}
                    >Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>

      {/* Add/Edit Account Form */}
      <div className="flex items-center justify-center min-h-screen">
        <div className="bg-blue-700 p-6 rounded-md shadow-md w-80">
          <h3 className="text-xl text-white font-semibold mb-4">
            {editingId ? "Edit Account" : "Add Account"}
          </h3>
          <form onSubmit={(e) => {
            e.preventDefault();
            handleAddOrUpdateAccount();
          }}>
            <div className="mb-4">
              <label className="block font-medium mb-2">Account Name</label>
              <input
                type="text"
                name="accountName"
                value={form.accountName}
                onChange={handleInputChange}
                className="w-full border border-gray-300 rounded px-3 py-2"
                required
              />
            </div>
            <div className="mb-4">
              <label className="block font-medium mb-2">Account Type</label>
              <select
                name="accountType"
                value={form.accountType}
                onChange={handleInputChange}
                className="w-full border border-gray-300 rounded px-3 py-2"
              >
                <option value="SAVINGS">SAVINGS</option>
                <option value="CHECKING">CHECKING</option>
                <option value="CREDIT">CREDIT</option>
                <option value="CASH">CASH</option>
              </select>
            </div>
            <div className="mb-4">
              <label className="block font-medium mb-2">Starting Balance</label>
              <input
                type="number"
                name="balance"
                value={form.balance}
                onChange={handleInputChange}
                className="w-full border border-gray-300 rounded px-3 py-2"
                required
              />
            </div>
            <button
              type="submit"
              className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-red-600"
            >
              {editingId ? "Update Account" : "Add Account"}
            </button>
          </form>
        </div>
      </div>
    </div>
  )
}

export default Accounts